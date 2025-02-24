package nl.han.se.cnp.bewd;

import nl.han.se.cnp.bewd.annotations.DiyGetMapping;
import nl.han.se.cnp.bewd.annotations.DiyAutowired;
import nl.han.se.cnp.bewd.annotations.DiyRestController;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/**
 * This class gives a brief demonstration of the possible Java Code behind the mysterious workings of Spring and
 * Dependency Injection. And by doing so, hopefully clarifies some of the magic that junior Software Engineers might
 * experience.
 * <p>
 * This Class does the following things:
 * <ul>
 *     <li>It scans all Classes in Package {@code nl.han.se.cnp.bewd} for the Annotation {@link DiyRestController}</li>
 *     <li>It creates instances of all found Classes</li>
 *     <li>It scans all Instances for methods that are annotated with {@link DiyAutowired}</li>
 *     <li>It determines the type of the parameter of the method, annotated with {@link DiyAutowired}</li>
 *     <li>It creates a new instance with the same type as that parameter</li>
 *     <li>It calls the method annotated with {@link DiyAutowired} with that newly created instance</li>
 *     <li>It scans all Instances for methods that are annotated with {@link DiyGetMapping}</li>
 *     <li>It calls all methods annotated with {@link DiyGetMapping}</li>
 * </ul>
 * <p>
 * Notice how this code is rather limited and assumes a lot about the following things:
 * <ul>
 *     <li>Only Setter injection is supported. Constructor en field injection is not</li>
 *     <li>The Constructor of the class annotated with {@link DiyRestController} and the dependency must be empty </li>
 * </ul>
 */
public class DiyDIRunner
{
    private static final String PACKAGE_TO_SCAN = "nl.han.se.cnp.bewd";

    private Reflections ref;

    public static void main(String[] args)
    {
        var runner = new DiyDIRunner();
        runner.runDiyApplication();
    }

    private void runDiyApplication()
    {
        try
        {
            var restClasses = findAllRESTClasses();
            var restInstances = createRESTInstances(restClasses);

            for (var instance : restInstances)
            {
                scanForInjectableSettersAndInject(instance);
            }

            for (var instance : restInstances)
            {
                scanForGetMethodsAndCall(instance);
            }

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

    private Set<Class<?>> findAllRESTClasses()
    {
        System.out.println("Scanning for classes...");

        // Create a new instance of Reflections. This is a helper class that is able
        // to find classes and their methods.
        ref = new Reflections(DiyDIRunner.PACKAGE_TO_SCAN);

        // Get all classes annotated with @DiyPath. This will gather
        // all classes in "packageToScan" for classes annotated with "DiyPath".
        var restClassInjector = DiyRestController.class;
        var restClasses = ref.getTypesAnnotatedWith(restClassInjector);

        // Print info on all found classes. Just for convenience...
        for (Class<?> clazz : restClasses)
        {
            System.out.printf("Found class annotated with %s: %s\n", restClassInjector.getSimpleName(), clazz.getSimpleName());
        }

        return restClasses;
    }

    public Set<Object> createRESTInstances(Set<Class<?>> classes) throws NoSuchMethodException, IllegalAccessException,
            InstantiationException, InvocationTargetException
    {
        // Create the HashSet that will be filled with all created Instances
        var restInstances = new HashSet<>();

        for (var clazz : classes)
        {
            // Get the Constructor from the Class
            var constructor = clazz.getConstructor();
            
            // Use the Constructor to create a new Instance
            var object = constructor.newInstance();

            // Add the created instance to the HashSet
            restInstances.add(object);

            // Report proudly
            System.out.println("Created instance: " + object.getClass().getSimpleName());
        }

        return restInstances;
    }

    private void scanForInjectableSettersAndInject(Object instance) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
    {
        var classToScan = instance.getClass();

        // Report proudly
        System.out.printf("Now scanning instance: %s to perform Dependency Injection.\n", classToScan.getSimpleName());

        // Iterate though all methods from the given Object
        for (var method : instance.getClass().getMethods())
        {
            var setterInjector = DiyAutowired.class;
            // Only continue for those that have the "DiyInject" annotation
            if (method.isAnnotationPresent(setterInjector))
            {
                System.out.printf("Found method annotated with %s: %s in class %s\n", setterInjector.getSimpleName(),
                        method.getName(), classToScan.getSimpleName());
                // Get the parameter of the method, so we can use it to create an Instance and perform
                // Dependency Injection
                Class<?>[] parameterTypes = method.getParameterTypes();

                // We assume the method has exactly one parameter of class type interface
                var classOfParameter = parameterTypes[0];

                // Find the implementing class of the interface
                var implementingClassOfParameter = ref.getSubTypesOf(classOfParameter).iterator().next();

                // Again we create an instance through the constructor
                var constructorOfDependency = implementingClassOfParameter.getConstructor();
                var instanceOfDependency = constructorOfDependency.newInstance();

                // In this case, we pass the dependency as the second argument of the invoke method
                method.invoke(instance, instanceOfDependency);

                System.out.printf("Create instance of %s to be injected into %s\n", classOfParameter.getSimpleName(),
                        method.getName());
            }
        }
    }

    private void scanForGetMethodsAndCall(Object instance) throws InvocationTargetException, IllegalAccessException
    {
        for (var method : instance.getClass().getMethods())
        {
            if (method.isAnnotationPresent(DiyGetMapping.class))
            {
                var response = method.invoke(instance);

                System.out.printf("Calling method %s(), gives response: %s\n", method.getName(), response);
            }
        }
    }
}
