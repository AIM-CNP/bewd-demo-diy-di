module demo.diy.di {
    requires org.reflections;

    exports nl.han.se.cnp.bewd.annotations to org.reflections;
    exports nl.han.se.cnp.bewd.resources to org.reflections;
    exports nl.han.se.cnp.bewd.services to org.reflections;
}
