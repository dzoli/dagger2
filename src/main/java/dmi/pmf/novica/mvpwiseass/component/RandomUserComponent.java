package dmi.pmf.novica.mvpwiseass.component;

import com.squareup.picasso.Picasso;

import dagger.Component;
import dmi.pmf.novica.mvpwiseass.RandomUsersApi;
import dmi.pmf.novica.mvpwiseass.annotation.RandomUserApplicationScope;
import dmi.pmf.novica.mvpwiseass.module.PicassoModule;
import dmi.pmf.novica.mvpwiseass.module.RandomUserModule;

// we need to educate component where to find this top level dependencies
@RandomUserApplicationScope
@Component(modules = {RandomUserModule.class, PicassoModule.class})
public interface RandomUserComponent {

    /*
     *   A component will act as a public interface
     *   for your entire dependency graph.
     *
     *   Best practice is to expose only top level
     *   dependency under the hood.
     *
     *   Top level dependencies are dependencies that
     *   nobody want! Only they have own dependencies!
     */

    RandomUsersApi getRandomUserApi();

    Picasso getPicasso();

}
