/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.drltext.backend.server.indexing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.drools.workbench.screens.drltext.type.DRLResourceTypeDefinition;
import org.kie.soup.project.datamodel.oracle.ProjectDataModelOracle;
import org.kie.workbench.common.services.datamodel.backend.server.service.DataModelService;
import org.kie.workbench.common.services.refactoring.backend.server.indexing.DefaultIndexBuilder;
import org.kie.workbench.common.services.refactoring.backend.server.indexing.drools.AbstractDrlFileIndexer;
import org.uberfire.backend.server.util.Paths;
import org.uberfire.java.nio.file.Path;

@ApplicationScoped
public class DrlFileIndexer extends AbstractDrlFileIndexer {

    @Inject
    protected DataModelService dataModelService;

    @Inject
    protected DRLResourceTypeDefinition drlType;

    @Override
    public boolean supportsPath(final Path path) {
        return drlType.accept(Paths.convert(path));
    }

    @Override
    public DefaultIndexBuilder fillIndexBuilder(final Path path) throws Exception {
        final String drl = ioService.readAllString(path);

        return fillDrlIndexBuilder(path, drl);
    }

    @Override
    protected ProjectDataModelOracle getProjectDataModelOracle(Path path) {
        return dataModelService.getProjectDataModel(Paths.convert(path));
    }
}
