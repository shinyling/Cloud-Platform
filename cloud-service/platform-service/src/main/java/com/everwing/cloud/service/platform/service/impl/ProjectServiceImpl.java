package com.everwing.cloud.service.platform.service.impl;

import com.everwing.cloud.service.platform.entity.Project;
import com.everwing.cloud.service.platform.mapper.ProjectMapper;
import com.everwing.cloud.service.platform.service.IProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

}
