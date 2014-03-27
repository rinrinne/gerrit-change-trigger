#!/bin/bash

ROOT=${PWD}
VERSION=$1

# Checkout tag
cd gerrit
git checkout -b ${VERSION} refs/tags/v${VERSION}
cd ..

# Add Symbolic link
ln -fns ${ROOT} gerrit/plugins/raise-patch

# Build
cd gerrit
${ROOT}/buck/bin/buck build plugins/raise-patch:raise-patch
