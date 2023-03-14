package org.gecko.reformer.handler;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.constant.OpType;
import org.gecko.reformer.domain.DataChangeDO;
import org.gecko.reformer.domain.SourceDO;

/**
 * sql数据库数据处理抽象工厂
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
@Slf4j
public abstract class AbsSqlHandler<T> implements IHandler<T, DataChangeDO> {

    /*** 更新元数据 **/
    private SourceDO source;

    @Override
    public void handlerData(DataChangeDO data, Class<T> modelClazz) {
        final OpType op = data.getOp();
        source = data.getSource();
        String before = data.getBefore();
        String after = data.getAfter();
        T beforeDto;
        T afterDto;
        switch (op) {
            case READ:
            case CREATE:
                afterDto = JSONUtil.toBean(after, modelClazz);
                insert(afterDto);
                break;
            case UPDATE:
                beforeDto = JSONUtil.toBean(before, modelClazz);
                afterDto = JSONUtil.toBean(after, modelClazz);
                update(beforeDto, afterDto);
                break;
            case DELETE:
                beforeDto = JSONUtil.toBean(before, modelClazz);
                delete(beforeDto);
                break;
            default:
                log.info("操作类型为=={},数据=={}", op, data);
                break;
        }
    }

    /**
     * 获取更新元数据
     *
     * @param
     * @return org.gecko.reformer.domain.SourceDO
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    protected SourceDO getSource() {
        return source;
    }

    /**
     * 新增
     *
     * @param t
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    public abstract void insert(T t);

    /**
     * 修改
     *
     * @param before
     * @param after
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    public abstract void update(T before, T after);

    /**
     * 删除
     *
     * @param t
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    public abstract void delete(T t);
}
