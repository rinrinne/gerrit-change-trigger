gerrit-raise-patch: Gerrit Raise Patchset plugin
==================

* Author: rinrinne a.k.a. rin_ne
* Repository: http://github.com/rinrinne/gerrit-raise-patch
* Release: http://github.com/rinrinne/gerrit-raise-patch/releases

[![Build Status](https://travis-ci.org/rinrinne/gerrit-raise-patch.png?branch=master)](https://travis-ci.org/rinrinne/gerrit-raise-patch)

Synopsis
----------------------

This is a Gerrit plugin.

This can raise `patchset-created` event to gerrit event stream.
`Raise Event` button is added to revision view.

This plugin works on Gerrit 2.8 or later.

Environments
---------------------

* `linux`
  * `java-1.7`
    * `gradle`

Build
---------------------

To build plugin with maven.

    ./gradlew build

Using another version API
--------------------------

Now avaliable for Gerrit 2.9 by default. If you want to use it on another version of Gerrit, please try the below.

    ./gradlew build -PapiVersion=2.8


History
---------------------

* 1.1
  * Add gradle support
  * Remove maven support

* 1.0
  *  First release

License
---------------------

The Apache Software License, Version 2.0

Copyright
---------------------

Copyright (c) 2014 rinrinne a.k.a. rin_ne
