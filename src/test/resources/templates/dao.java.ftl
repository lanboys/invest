package ${daoPackage};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};

import com.bing.lan.invest.base.BaseDao;
import org.springframework.stereotype.Repository;


/**
 * <p>
 * ${table.comment!} dao
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Repository
public class ${entity}${daoSuffix} extends BaseDao<${table.mapperName},${entity}> {

}
