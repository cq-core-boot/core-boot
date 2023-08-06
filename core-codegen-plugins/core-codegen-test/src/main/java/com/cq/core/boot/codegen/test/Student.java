package com.cq.core.boot.codegen.test;

import com.cq.core.boot.codegen.processor.api.*;
import com.cq.core.boot.codegen.processor.controller.GenController;
import com.cq.core.boot.codegen.processor.creator.GenCreator;
import com.cq.core.boot.codegen.processor.creator.IgnoreCreator;
import com.cq.core.boot.codegen.processor.mapper.GenMapper;
import com.cq.core.boot.codegen.processor.query.GenQuery;
import com.cq.core.boot.codegen.processor.repository.GenRepository;
import com.cq.core.boot.codegen.processor.service.GenService;
import com.cq.core.boot.codegen.processor.service.GenServiceImpl;
import com.cq.core.boot.codegen.processor.updater.GenUpdater;
import com.cq.core.boot.codegen.processor.updater.IgnoreUpdater;
import com.cq.core.boot.codegen.processor.vo.GenVo;
import com.cq.core.boot.commons.constants.ValidStatus;
import com.cq.core.boot.jpa.converter.ValidStatusConverter;
import com.cq.core.boot.jpa.support.BaseJpaAggregate;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@GenVo(pkgName = "com.cq.core.boot.codegen.test.vo")
@GenCreator(pkgName = "com.cq.core.boot.codegen.test.creator")
@GenUpdater(pkgName = "com.cq.core.boot.codegen.test.updater")
@GenRepository(pkgName = "com.cq.core.boot.codegen.test.repository")
@GenService(pkgName = "com.cq.core.boot.codegen.test.service")
@GenServiceImpl(pkgName = "com.cq.core.boot.codegen.test.service")
@GenQuery(pkgName = "com.cq.core.boot.codegen.test.query")
@GenMapper(pkgName = "com.cq.core.boot.codegen.test.mapper")
@GenController(pkgName = "com.cq.core.boot.codegen.test.controller")
@GenCreateRequest(pkgName = "com.cq.core.boot.codegen.test.api.request", sourcePath = Constants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.cq.core.boot.codegen.test.api.request", sourcePath = Constants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.cq.core.boot.codegen.test.api.request", sourcePath = Constants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.cq.core.boot.codegen.test.api.response", sourcePath = Constants.GEN_API_SOURCE)
@GenFeign(pkgName = "com.cq.core.boot.codegen.test.api.service", sourcePath = Constants.GEN_API_SOURCE, serverName = "srv")
@Entity
@Table(name = "")
@Data
public class Student extends BaseJpaAggregate {

    @Convert(converter = ValidStatusConverter.class)
    @IgnoreUpdater
    @IgnoreCreator
    private ValidStatus validStatus;

    private Long age;

    public void init() {
        setValidStatus(ValidStatus.VALID);
    }

    public void valid() {
        setValidStatus(ValidStatus.VALID);
    }

    public void invalid() {
        setValidStatus(ValidStatus.INVALID);
    }
}