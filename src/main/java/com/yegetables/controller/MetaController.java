package com.yegetables.controller;

import com.yegetables.controller.dto.ApiResult;
import com.yegetables.controller.dto.ApiResultStatus;
import com.yegetables.pojo.Meta;
import com.yegetables.utils.JsonTools;
import com.yegetables.utils.StringTools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/meta")
public class MetaController extends BaseController {

    /**
     * 查询都有哪些分类
     *
     * @param
     * @return
     */
    @RequestMapping("/allCategory")
    @ResponseBody
    public String allCategory() {
        return allType("category");
    }

    /**
     * 查询都有哪些标签
     *
     * @param
     * @return
     */
    @RequestMapping("/allTag")
    @ResponseBody
    public String allTag() {
        //        return allType("{\"type\":\"tag\"}");
        return allType("tag");
    }


    /**
     * 查询都有哪些类型
     *
     * @param
     * @return
     */
    @RequestMapping("/allType")
    @ResponseBody
    public String allType(@RequestBody String type) {
        //        log.warn("type:" + type);
        if (JsonTools.isJson(type)) type = StringTools.mapGetStringKey("type", jsonTools.JsonToMap(type));

        type = StringTools.toOkString(type);
        if (type.equals("")) return new ApiResult<List<Meta>>().code(ApiResultStatus.Error).message("参数错误").toString();
        List<Meta> metas = metaService.allType(type);
        ApiResult<List<Meta>> result = new ApiResult<List<Meta>>().code(ApiResultStatus.Success).message("查询成功").data(metas);
        return result.toString();
    }

}