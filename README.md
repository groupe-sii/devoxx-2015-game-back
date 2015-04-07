# devoxx-2015-game-back

Game development for devoxx 2015. This is the main repository for the whole server code. Typically, you would want to grap the code and launch the server. To add an extension, you **should not** modify anything but the [ext  submodule](https://github.com/groupe-sii/devoxx-2015-game-back-ext).


## Requirements

### Sofwtares installed on your computer

* [git](http://git-scm.com/downloads)
* [Java JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [maven 3.x](https://maven.apache.org/download.cgi)
 

### Other prerequisites

A local git repository with a correct configuration (cf . [documentation](http://git-scm.com/book/en/v2/Getting-Started-Installing-Git) )


## Install and run the server

The server for the game can run standalone. You can either run it using the built version or build yourself from sources.


### Get the WAR file

#### Download the built version (once)

You can download the generated version from our Jenkins: [survival-game.war](http://jenkins.devoxx.sii.fr/view/Devoxx/job/devoxx-game-back/lastSuccessfulBuild/artifact/survival-game/target/survival-game-0.0.1-SNAPSHOT.war).


#### Build yourself

Create a folder for your workspace. For instance ```devoxx-2015``` and cd into it.

````
mkdir devoxx-2015 && cd $_
````

##### Grab the code (once)

In order to get a clean local repository, you should type something similar to:

```
devoxx-2015> $ git clone https://github.com/groupe-sii/devoxx-2015-game-back
```

The server repo will be cloned in a folder ```devoxx-2015-game-back ```unless you give it another name.

##### Compile and install the server (once)

Our server comes with a spring-boot standalone integration. 
Spring-boot will just check for the server artifacts in your local maven repository. 
So you just need to type : 

```
devoxx-2015/devoxx-2015-game-back> mvn clean install
```

### Run the server

The WAR file only provides the logic of the game but not the extensions. You now need to get [sources of extensions](https://github.com/groupe-sii/devoxx-2015-game-back-ext) and [follow these instructions to build them](https://github.com/groupe-sii/devoxx-2015-game-back-ext/blob/master/README.md#get-the-sources).

The WAR can be run either in an existing web server or directly as Java application.
The recommended way is to run as Java application.


#### Running the downloaded WAR

You can run the WAR file with the generated JAR for extensions by executing the following command:
```
devoxx-2015/devoxx-2015-game-back> cd ..
devoxx-2015> java -Dgame.extensions.path=devoxx-2015-game-back-ext/target/survival-extensions-0.0.1-SNAPSHOT.jar -jar survival-game-0.0.1-SNAPSHOT.war
```

We assume that the downloaded WAR and the sources of extensions are in the same folder.


#### Running from sources

You can use the maven plugin provided by Spring Boot to help us running the server. You'll need to run from the *survival-game* directory :

```
devoxx-2015/devoxx-2015-game-back> cd survival-game
devoxx-2015/devoxx-2015-game-back/survival-game> mvn spring-boot:run
```


### Get the client

To view the client, you need to either build it from sources or directly download the generated zip.


#### From sources

To see the game running, you need to download the [web client game](https://github.com/groupe-sii/devoxx-2015-game-front).

#### Download it

If you don't want to create front extensions, you can simply [download the client](http://jenkins.devoxx.sii.fr/job/devoxx-game-front/ws/dist/*zip*/dist.zip). Then you have to configure your HTTP server (Apache for example) to serve the static html page.


### Hot reload

When you create your own extensions by following [this guide](https://github.com/groupe-sii/devoxx-2015-game-back-ext/blob/master/README.md#devoxx-2015-game-back-ext), the server doesn't need to be restarted. It will automatically detect changes and reload your extensions.


## What's inside ? 

Feel free to take a look at the different javadocs :
* Main module and game API : http://game.javadoc.devoxx.sii.fr/core/
* Game extensions API : http://game.javadoc.devoxx.sii.fr/extensions/
* Provided WebSocket interface for GUI clients : http://game.javadoc.devoxx.sii.fr/web/

