package com.sdg.cmdb.controller;


import com.sdg.cmdb.domain.HttpResult;

import com.sdg.cmdb.domain.gitlab.v1.GitlabWebHooks;
import com.sdg.cmdb.service.GitlabService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/gitlab")
public class GitlabController {

    static public final String HOOK_V1 = "v1";


    @Resource
    private GitlabService gitlabService;

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public HttpResult getVersion() {
        return new HttpResult(gitlabService.getVersion());
    }

    @RequestMapping(value = "/project/page", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult queryProjectPage(@RequestParam String name, @RequestParam String username,
                                       @RequestParam int page, @RequestParam int length) {
        return new HttpResult(gitlabService.getProjectPage(name, username, page, length));
    }

    @RequestMapping(value = "/project/update", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult updateProjects() {
        return new HttpResult(gitlabService.updateProjcets());
    }

    /**
     * http://oc.ops.yangege.cn/gitlab/v1/webHooks
     *
     * @param webhooks
     * @return
     */
    @RequestMapping(value = "/" + HOOK_V1 + "/webHooks", method = RequestMethod.POST)
    public HttpResult forWebHooksTriger(@RequestBody GitlabWebHooks webhooks) {
        return new HttpResult(gitlabService.webHooksV1(webhooks));
    }

    /**
     * http://oc.ops.yangege.cn/gitlab/v1/systemHooks
     */
    @RequestMapping(value = "/" + HOOK_V1 + "/systemHooks", method = RequestMethod.POST)
    public HttpResult forSystemHooksTriger(@RequestBody GitlabWebHooks webhooks) {
        return new HttpResult(gitlabService.webHooksV1(webhooks));
    }
}
