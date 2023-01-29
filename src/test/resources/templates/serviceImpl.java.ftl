package ${package.ServiceImpl};

import ${daoPackage}.${entity}${daoSuffix};
import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@Transactional
public class ${table.serviceImplName} implements ${table.serviceName} {

    @Autowired
    ${entity}${daoSuffix} dao;

}
