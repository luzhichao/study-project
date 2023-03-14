package org.gecko.reformer.handler;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.constant.OpMongoType;
import org.gecko.reformer.domain.MongoDataChangeDO;
import org.gecko.reformer.domain.MongoSourceDO;
import org.gecko.reformer.domain.MongoUpdateDescDO;
import org.gecko.reformer.domain.UpdateDTO;

/**
 * mongodb数据库处理抽象工厂
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
@Slf4j
public abstract class AbsMongoHandler<T> implements IHandler<T, MongoDataChangeDO> {

    private MongoSourceDO source;

    @Override
    public void handlerData(MongoDataChangeDO data, Class<T> modelClazz) {
        final OpMongoType op = data.getOp();
        final String fullDoc = data.getFullDoc();
        final MongoUpdateDescDO update = data.getUpdateDesc();
        final String id = data.getId();
        T after;
        switch (op) {
            case INSERT:
                after = BeanUtil.copyProperties(fullDoc, modelClazz);
                insert(id, after);
                break;
            case UPDATE:
                after = BeanUtil.copyProperties(fullDoc, modelClazz);
                final UpdateDTO dto = BeanUtil.copyProperties(update, UpdateDTO.class);
                update(id, dto, after);
                break;
            case DELETE:
                delete(id);
                break;
            default:
                log.info("操作类型为=={},数据=={}", op, data);
                break;
        }
    }

    /**
     * 获取数据变更元数据
     *
     * @param
     * @return org.gecko.reformer.domain.MongoSourceDO
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    protected MongoSourceDO getSource() {
        return source;
    }

    /**
     * 新增
     *
     * @param id
     * @param after 新增的数据
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    public abstract void insert(String id, T after);

    /**
     * 修改
     *
     * @param id
     * @param dto   更新详情
     * @param after 更新后的数据
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    public abstract void update(String id, UpdateDTO dto, T after);

    /**
     * 删除
     *
     * @param id 数据id
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    public abstract void delete(String id);
}
