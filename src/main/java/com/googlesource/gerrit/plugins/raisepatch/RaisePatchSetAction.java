// Copyright (C) 2014 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.googlesource.gerrit.plugins.raisepatch;

import com.google.gerrit.common.ChangeHooks;
import com.google.gerrit.extensions.restapi.AuthException;
import com.google.gerrit.extensions.restapi.BadRequestException;
import com.google.gerrit.extensions.restapi.ResourceConflictException;
import com.google.gerrit.extensions.restapi.RestReadView;
import com.google.gerrit.extensions.webui.UiAction;
import com.google.gerrit.reviewdb.server.ReviewDb;
import com.google.gerrit.server.change.RevisionResource;
import com.google.gerrit.server.data.AccountAttribute;
import com.google.gerrit.server.data.ChangeAttribute;
import com.google.gerrit.server.data.PatchSetAttribute;
import com.google.gerrit.server.events.EventFactory;
import com.google.gerrit.server.events.PatchSetCreatedEvent;
import com.google.inject.Inject;

public class RaisePatchSetAction implements UiAction<RevisionResource>, RestReadView<RevisionResource> {

  private final ChangeHooks changeHooks;
  private final EventFactory eventFactory;
  private final ReviewDb reviewDb;

  @Inject
  public RaisePatchSetAction(ChangeHooks changeHooks, EventFactory eventFactory, ReviewDb reviewDb) {
    this.changeHooks = changeHooks;
    this.eventFactory = eventFactory;
    this.reviewDb = reviewDb;
  }

  @Override
  public String apply(RevisionResource rrsc) throws AuthException, BadRequestException, ResourceConflictException,
  Exception {
    PatchSetCreatedEvent event = new PatchSetCreatedEvent();

    ChangeAttribute change = eventFactory.asChangeAttribute(rrsc.getChange());
    PatchSetAttribute patchSet = eventFactory.asPatchSetAttribute(rrsc.getPatchSet());
    AccountAttribute uploader = eventFactory.asAccountAttribute(reviewDb.accounts().get(rrsc.getPatchSet().getUploader()));

    event.change = change;
    event.patchSet = patchSet;
    event.uploader = uploader;

    changeHooks.postEvent(rrsc.getChange(), event, reviewDb);

    return "Raised.";
  }

  @Override
  public com.google.gerrit.extensions.webui.UiAction.Description getDescription(RevisionResource rrsc) {
    return new Description()
    .setLabel("Raise Event")
    .setTitle("Raise patchset-created event to event stream.");
  }

}
