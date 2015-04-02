# devoxx-2015-game-back
Game development for devoxx 2015. This is the main repository for the whole server code. Typically, you would want to grap the code and lauch the server. To add an extension, you **should not** modify anything but the [ext  submodule](https://github.com/groupe-sii/devoxx-2015-game-back-ext).

## Requirements
### Sofwtares installed on your computer :
* git
* Java JDK 1.8
* maven 3.x
 

### Other prerequisites
- a local git repository with a correct configuration (cf . [documentation](http://git-scm.com/book/en/v2/Getting-Started-Installing-Git) )

## Install and run the server
### Grab the code (once)
Warning : The git repository contains some submodules. In order to get a clean local repository, you should do something similar to :
```~/git> $ git clone --recursive https://github.com/groupe-sii/devoxx-2015-game-back```
```~/git> cd devoxx-2015-game-back```
```~/git/devoxx-2015-game-back> git submodule foreach --recursive git checkout master```
### Compile and install the server (once)
Our server comes with a spring-boot standalone integration. 
Spring-boot will just check for the server artifacts in your local maven repository. So you just need a 
```~/git/devoxx-2015-game-back> mvn clean install```
### Run the server
Now each time you would want to run the server, you'll need to run from the *survival-game* directory:
```~/git/devoxx-2015-game-back> mvn spring:boot```
