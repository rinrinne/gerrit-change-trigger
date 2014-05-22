#!/bin/bash

ROOT=${PWD}
TYPE=$1
VERSION=$2

if [ "$TYPE" = "BUCK" ]; then
  # Gerrit 
  git clone https://gerrit.googlesource.com/gerrit 

  # Checkout tag
  if [ "$VERSION" != "HEAD" ]; then
    cd gerrit
    git checkout -b ${VERSION} refs/tags/v${VERSION}
    cd ..
  fi

  # Add Symbolic link
  ln -fns ${ROOT} gerrit/plugins/raise-patch

  # Build
  cd gerrit
  ${ROOT}/buck/bin/buck build plugins/raise-patch:raise-patch
else
  if [ "$VERSION" != "HEAD" ]; then
    ./gradlew build -PapiVersion=$VERSION
  fi
fi
