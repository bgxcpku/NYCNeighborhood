NYCNeighborhood
===============

A foursquare connected app showing the neighborhood's name of your checkin.

http://nyneighborhood.appspot.com/

This application use:

   * [Play! Framework](http://www.playframework.org/)
   * [Google App Engine](https://appengine.google.com)
   * [Foursquare API](https://developer.foursquare.com/)
   * [StreetEasy API](http://streeteasy.com/nyc/api/info)
   * [Guice](http://code.google.com/p/google-guice/)
   * [Foursquare API v2 for Java](http://code.google.com/p/foursquare-api-java/)
   * [GSon](http://code.google.com/p/google-gson/)

Before starting
---------------------

   * Install [Play Framework 1.2](http://www.playframework.org/download)
   * Install [Google App Engine SDK](https://developers.google.com/appengine/downloads#Google_App_Engine_SDK_for_Java)

###App Engine:

   * Go to your [Google App Engine account](https://appengine.google.com/) and create a new application (good luck to finding an available name)

###Foursquare:

  1. Go to [Foursquare](https://foursquare.com/oauth) and register a new consumer
  2. Be sure to allow your consumer to receive push notifications
  3. for the Push URL: `https://your_gae_application.appspot.com/psh`


Guice Module for Play!
----------------------

[Guice Module Documentation](http://www.playframework.org/modules/guice-1.2/home)


Don't forget to delete the test directory from the guice module or will have errors :)

Author
-------

**Laurent Bouin**

+ http://twitter.com/laurentbouin
+ http://about.me/laurentbouin