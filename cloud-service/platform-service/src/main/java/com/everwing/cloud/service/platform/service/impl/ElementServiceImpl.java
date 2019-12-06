package com.everwing.cloud.service.platform.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.everwing.cloud.service.platform.entity.Element;
import com.everwing.cloud.service.platform.mapper.ElementMapper;
import com.everwing.cloud.service.platform.service.IElementService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shiny
 * @since 2019-12-05
 */
@Service
public class ElementServiceImpl extends ServiceImpl<ElementMapper, Element> implements IElementService {

}
