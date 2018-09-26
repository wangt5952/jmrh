if(UserTypeEnum.Expert.key() == e.getFormType()) {
    if(StringUtils.isEmpty(e.getName()) || StringUtils.isEmpty(e.getCode())) {
        voRes.setFail(voRes);
        voRes.setMessage("专家姓名和身份证号不能为空");
        return voRes;
    }
    e.setName(json.getString("name"));//专家姓名
    e.setCode(json.getString("code"));//专家身份证号
    // 性别
    if (WzStrUtil.isBlank(json.getString("sex"))
            || (!"1".equals(json.getString("sex")) && !"0".equals(json.getString("sex")))) {
        voRes.setFail(voRes);
        voRes.setMessage("性别不能为空或信息错误，请按照正确的样例填写");
        return voRes;
    }
    // 所在区域
    if (WzStrUtil.isBlank(json.getString("country"))) {
        voRes.setFail(voRes);
        voRes.setMessage("地域不能为空，请按照正确的样例填写");
        return voRes;
    }
    JSONArray carr = null;
    try {
        carr = JSON.parseArray(json.getString("country"));
    } catch (Exception ex) {
        carr = null;
    }
    if (null == carr || 0 == carr.size() || 3 < carr.size()) {
        voRes.setFail(voRes);
        voRes.setMessage("地域录入信息错误，请按照正确的样例填写");
        return voRes;
    }
    // 学历
    if (WzStrUtil.isBlank(json.getString("edu"))) {
        voRes.setFail(voRes);
        voRes.setMessage("学历不能为空，请按照正确的样例填写");
        return voRes;
    }
    String xldef = "5,6,7,99";
    if (0 > xldef.indexOf(json.getString("edu"))) {
        voRes.setFail(voRes);
        voRes.setMessage("学历录入信息错误，请按照正确的样例填写");
        return voRes;
    }
    // 学位
    if (WzStrUtil.isBlank(json.getString("academic"))) {
        voRes.setFail(voRes);
        voRes.setMessage("学位不能为空，请按照正确的样例填写");
        return voRes;
    }
    String xwdef = "1,2,3,99";
    if (0 > xwdef.indexOf(json.getString("academic"))) {
        voRes.setFail(voRes);
        voRes.setMessage("学位录入信息错误，请按照正确的样例填写");
        return voRes;
    }
    // 研究领域
    if (WzStrUtil.isBlank(json.getString("research_field"))) {
        voRes.setFail(voRes);
        voRes.setMessage("领域不能为空，请按照正确的样例填写");
        return voRes;
    }
    JSONArray darr = null;
    try {
        darr = JSON.parseArray(json.getString("research_field"));
    } catch (Exception ex) {
        darr = null;
    }
    String lydef = "1,2,3,4,5,6,7,99";
    if (null != darr && 0 < darr.size()) {
        for (int i = 0; i < darr.size(); i++) {
            if (0 > lydef.indexOf((String) darr.get(i))) {
                voRes.setFail(voRes);
                voRes.setMessage("领域录入信息错误，请按照正确的样例填写");
                return voRes;
            }
        }
    } else {
        voRes.setFail(voRes);
        voRes.setMessage("领域不能为空，请按照正确的样例填写");
        return voRes;
    }
    // 手机号码
    if (!WzStrUtil.isMobile(json.getString("mobilephone"))
            && !WzStrUtil.isPhone(json.getString("mobilephone"))) {
        voRes.setFail(voRes);
        voRes.setMessage("格式错误，请输入正确的手机号码");
        return voRes;
    }
    // 邮箱
    if (!WzStrUtil.isEmail(json.getString("fdemail"))) {
        voRes.setFail(voRes);
        voRes.setMessage("格式错误，请输入正确的电子邮箱");
        return voRes;
    }
}else if(UserTypeEnum.Enterprise.key() == e.getFormType()) {
    if(WzStrUtil.isBlank(e.getName()) || WzStrUtil.isBlank(e.getCode())) {
        voRes.setFail(voRes);
        voRes.setMessage("企业名称和统一社会信用代码不能为空");
        return voRes;
    }
    // 所在区域
    if (WzStrUtil.isBlank(json.getString("country"))) {
        voRes.setFail(voRes);
        voRes.setMessage("地域不能为空，请按照正确的样例填写");
        return voRes;
    }
    JSONArray carr = null;
    try {
        carr = JSON.parseArray(json.getString("country"));
    } catch (Exception ex) {
        carr = null;
    }
    if (null == carr || 0 == carr.size() || 3 < carr.size()) {
        voRes.setFail(voRes);
        voRes.setMessage("地域录入信息错误，请按照正确的样例填写");
        return voRes;
    }
    // 是否上市
    if (WzStrUtil.isBlank(json.getString("registerMarket"))) {
        voRes.setFail(voRes);
        voRes.setMessage("是否上市不能为空，请按照正确的样例填写");
        return voRes;
    }
    String ssdef = "0,1";
    if (0 > ssdef.indexOf(json.getString("registerMarket"))) {
        voRes.setFail(voRes);
        voRes.setMessage("是否上市录入信息错误，请按照正确的样例填写");
        return voRes;
    }
    // 所属领域
    if (WzStrUtil.isBlank(json.getString("domain"))) {
        voRes.setFail(voRes);
        voRes.setMessage("领域不能为空，请按照正确的样例填写");
        return voRes;
    }
    JSONArray darr = null;
    try {
        darr = JSON.parseArray(json.getString("domain"));
    } catch (Exception ex) {
        darr = null;
    }
    String lydef = "1,2,3,4,5,6,99";
    if (null != darr && 0 < darr.size()) {
        for (int i = 0; i < darr.size(); i++) {
            if (0 > lydef.indexOf((String) darr.get(i))) {
                voRes.setFail(voRes);
                voRes.setMessage("领域录入信息错误，请按照正确的样例填写");
                return voRes;
            }
        }
    } else {
        voRes.setFail(voRes);
        voRes.setMessage("领域不能为空，请按照正确的样例填写");
        return voRes;
    }
    // 联系人姓名
    if (WzStrUtil.isBlank(json.getString("lxname"))) {
        voRes.setFail(voRes);
        voRes.setMessage("联系人姓名不能为空，请按照正确的样例填写");
        return voRes;
    }
    boolean isPhoneOk = true;
    boolean isEmailOk = true;
    // 联系人手机
    if (WzStrUtil.isBlank(json.getString("lxphone"))) {
        isPhoneOk = false;
    } else {
        if (!WzStrUtil.isMobile(json.getString("lxphone"))
                && !WzStrUtil.isPhone(json.getString("lxphone"))) {
            voRes.setFail(voRes);
            voRes.setMessage("格式错误，请输入正确的手机或电话号码");
            return voRes;
        }
    }
    // 联系人邮箱
    if (WzStrUtil.isBlank(json.getString("lxemail"))) {
        isEmailOk = false;
    } else {
        if (!WzStrUtil.isEmail(json.getString("lxemail"))) {
            voRes.setFail(voRes);
            voRes.setMessage("格式错误，请输入正确的电子邮箱");
            return voRes;
        }
    }
    if (!isEmailOk && !isPhoneOk) {
        voRes.setFail(voRes);
        voRes.setMessage("手机或电子邮箱必填一项");
        return voRes;
    }

}else if(UserTypeEnum.Service.key() == e.getFormType()) {
    if(StringUtils.isEmpty(e.getName()) || StringUtils.isEmpty(e.getCode())) {
        voRes.setFail(voRes);
        voRes.setMessage("服务机构名称和统一社会信用代码不能为空");
        return voRes;
    }
    // 所在地区
    if (WzStrUtil.isBlank(json.getString("country"))) {
        voRes.setFail(voRes);
        voRes.setMessage("地域不能为空，请按照正确的样例填写");
        return voRes;
    }
    JSONArray carr = null;
    try {
        carr = JSON.parseArray(json.getString("country"));
    } catch (Exception ex) {
        carr = null;
    }
    if (null == carr || 0 == carr.size() || 3 < carr.size()) {
        voRes.setFail(voRes);
        voRes.setMessage("地域录入信息错误，请按照正确的样例填写");
        return voRes;
    }
    // 机构性质
    if (WzStrUtil.isBlank(json.getString("registerNature"))) {
        voRes.setFail(voRes);
        voRes.setMessage("机构性质不能为空，请按照正确的样例填写");
        return voRes;
    }
    JSONArray xzarr = null;
    try {
        xzarr = JSON.parseArray(json.getString("registerNature"));
    } catch (Exception ex) {
        xzarr = null;
    }
    String xzdef = "1,2,3,99";
    if (null != xzarr && 0 < xzarr.size()) {
        for (int i = 0; i < xzarr.size(); i++) {
            if (0 > xzdef.indexOf((String) xzarr.get(i))) {
                voRes.setFail(voRes);
                voRes.setMessage("机构性质录入信息错误，请按照正确的样例填写");
                return voRes;
            }
        }
    } else {
        voRes.setFail(voRes);
        voRes.setMessage("机构性质不能为空，请按照正确的样例填写");
        return voRes;
    }
    // 机构类别
    if (WzStrUtil.isBlank(json.getString("orgType"))) {
        voRes.setFail(voRes);
        voRes.setMessage("机构类别不能为空，请按照正确的样例填写");
        return voRes;
    }
    JSONArray tyarr = null;
    try {
        tyarr = JSON.parseArray(json.getString("orgType"));
    } catch (Exception ex) {
        tyarr = null;
    }
    String tydef = "1,2,3,4,5,6,7,8,9,10,99";
    if (null != tyarr && 0 < tyarr.size()) {
        for (int i = 0; i < tyarr.size(); i++) {
            if (0 > tydef.indexOf((String) tyarr.get(i))) {
                voRes.setFail(voRes);
                voRes.setMessage("机构类别录入信息错误，请按照正确的样例填写");
                return voRes;
            }
        }
    } else {
        voRes.setFail(voRes);
        voRes.setMessage("机构类别不能为空，请按照正确的样例填写");
        return voRes;
    }
    // 机构联系人
    if (WzStrUtil.isBlank(json.getString("linkman"))) {
        voRes.setFail(voRes);
        voRes.setMessage("联系人姓名不能为空，请按照正确的样例填写");
        return voRes;
    }
    boolean isPhoneOk = true;
    boolean isEmailOk = true;
    // 联系人手机
    if (WzStrUtil.isBlank(json.getString("lxphone"))
            && WzStrUtil.isBlank(json.getString("lxtel"))) {
        isPhoneOk = false;
    } else {
        if (!WzStrUtil.isMobile(json.getString("lxphone"))
                && !WzStrUtil.isPhone(json.getString("lxtel"))) {
            voRes.setFail(voRes);
            voRes.setMessage("格式错误，请输入正确的手机或电话号码");
            return voRes;
        }
    }
    // 联系人邮箱
    if (WzStrUtil.isBlank(json.getString("lxemail"))) {
        isEmailOk = false;
    } else {
        if (!WzStrUtil.isEmail(json.getString("lxemail"))) {
            voRes.setFail(voRes);
            voRes.setMessage("格式错误，请输入正确的电子邮箱");
            return voRes;
        }
    }
    if (!isEmailOk && !isPhoneOk) {
        voRes.setFail(voRes);
        voRes.setMessage("手机或电子邮箱必填一项");
        return voRes;
    }
}else if(UserTypeEnum.College.key() == e.getFormType()) {
    if(StringUtils.isEmpty(e.getName()) || StringUtils.isEmpty(e.getCode())) {
        voRes.setFail(voRes);
        voRes.setMessage("高校院所名称和统一社会信用代码不能为空");
        return voRes;
    }
    // 所在区域
    if (WzStrUtil.isBlank(json.getString("country"))) {
        voRes.setFail(voRes);
        voRes.setMessage("地域不能为空，请按照正确的样例填写");
        return voRes;
    }
    JSONArray carr = null;
    try {
        carr = JSON.parseArray(json.getString("country"));
    } catch (Exception ex) {
        carr = null;
    }
    if (null == carr || 0 == carr.size() || 3 < carr.size()) {
        voRes.setFail(voRes);
        voRes.setMessage("地域录入信息错误，请按照正确的样例填写");
        return voRes;
    }
    if (WzStrUtil.isNotBlank(json.getString("lxmobile"))) {
        if (!WzStrUtil.isMobile(json.getString("lxmobile"))
                && !WzStrUtil.isPhone(json.getString("lxmobile"))) {
            voRes.setFail(voRes);
            voRes.setMessage("手机号码格式错误，请按照正确的样例填写");
            return voRes;
        }
    }
    if (WzStrUtil.isNotBlank(json.getString("lxemail"))) {
        if (!WzStrUtil.isEmail(json.getString("lxemail"))) {
            voRes.setFail(voRes);
            voRes.setMessage("邮箱格式错误，请按照正确的样例填写");
            return voRes;
        }
    }
}else if(UserTypeEnum.Results.key() == e.getFormType()) {
    if(WzStrUtil.isBlank(e.getReName())
            || WzStrUtil.isBlank(e.getName())
            || WzStrUtil.isBlank(e.getCode())) {
        voRes.setFail(voRes);
        voRes.setMessage("成果名称、成果单位名称及统一社会信用信息不能为空");
        return voRes;
    }
    // 所在地区
    if (WzStrUtil.isBlank(json.getString("country"))) {
        voRes.setFail(voRes);
        voRes.setMessage("地域不能为空，请按照正确的样例填写");
        return voRes;
    }
    JSONArray carr = null;
    try {
        carr = JSON.parseArray(json.getString("country"));
    } catch (Exception ex) {
        carr = null;
    }
    if (null == carr || 0 == carr.size() || 3 < carr.size()) {
        voRes.setFail(voRes);
        voRes.setMessage("地域录入信息错误，请按照正确的样例填写");
        return voRes;
    }
    boolean isFzOk = false;
    boolean isLxOk = false;
    if (WzStrUtil.isNotBlank(json.getString("fzname"))
            && ((WzStrUtil.isMobile(json.getString("fzphone")) || WzStrUtil.isPhone(json.getString("fzphone")))
                || WzStrUtil.isEmail(json.getString("fzemail")))) {
        isFzOk = true;
    }
    if (WzStrUtil.isNotBlank(json.getString("fzname2"))
            && ((WzStrUtil.isMobile(json.getString("fzphone2")) || WzStrUtil.isPhone(json.getString("fzphone2")))
                || WzStrUtil.isEmail(json.getString("fzemail2")))) {
        isLxOk = true;
    }
    if (!isFzOk && !isLxOk) {
        voRes.setFail(voRes);
        voRes.setMessage("负责人信息和联系人信息至少正确填写一套，请注意手机号码和邮箱的格式");
        return voRes;
    }
    // 所属领域
    if (WzStrUtil.isBlank(json.getString("domain"))) {
        voRes.setFail(voRes);
        voRes.setMessage("领域不能为空，请按照正确的样例填写");
        return voRes;
    }
    JSONArray darr = null;
    try {
        darr = JSON.parseArray(json.getString("domain"));
    } catch (Exception ex) {
        darr = null;
    }
    String lydef = "1,2,3,4,5,6,99";
    if (null != darr && 0 < darr.size()) {
        for (int i = 0; i < darr.size(); i++) {
            if (0 > lydef.indexOf((String) darr.get(i))) {
                voRes.setFail(voRes);
                voRes.setMessage("领域录入信息错误，请按照正确的样例填写");
                return voRes;
            }
        }
    } else {
        voRes.setFail(voRes);
        voRes.setMessage("领域不能为空，请按照正确的样例填写");
        return voRes;
    }
}else if(UserTypeEnum.Requirement.key() == e.getFormType()) {
    if(WzStrUtil.isBlank(e.getReName())
            || WzStrUtil.isBlank(e.getName())
            || WzStrUtil.isBlank(e.getCode())) {
        voRes.setFail(voRes);
        voRes.setMessage("需求名称、需求单位名称及统一社会信用信息不能为空");
        return voRes;
    }
    // 所在地区
    if (WzStrUtil.isBlank(json.getString("country"))) {
        voRes.setFail(voRes);
        voRes.setMessage("地域不能为空，请按照正确的样例填写");
        return voRes;
    }
    JSONArray carr = null;
    try {
        carr = JSON.parseArray(json.getString("country"));
    } catch (Exception ex) {
        carr = null;
    }
    if (null == carr || 0 == carr.size() || 3 < carr.size()) {
        voRes.setFail(voRes);
        voRes.setMessage("地域录入信息错误，请按照正确的样例填写");
        return voRes;
    }
    boolean isFzOk = false;
    boolean isLxOk = false;
    if (WzStrUtil.isNotBlank(json.getString("fzname"))
            && ((WzStrUtil.isMobile(json.getString("fzphone")) || WzStrUtil.isPhone(json.getString("fzphone")))
                || WzStrUtil.isEmail(json.getString("fzemail")))) {
        isFzOk = true;
    }
    if (WzStrUtil.isNotBlank(json.getString("fzname2"))
            && ((WzStrUtil.isMobile(json.getString("fzphone2")) || WzStrUtil.isPhone(json.getString("fzphone2")))
                || WzStrUtil.isEmail(json.getString("fzemail2")))) {
        isLxOk = true;
    }
    if (!isFzOk && !isLxOk) {
        voRes.setFail(voRes);
        voRes.setMessage("负责人信息和联系人信息至少正确填写一套，请注意手机号码和邮箱的格式");
        return voRes;
    }

    // 所属领域
    if (WzStrUtil.isBlank(json.getString("domain"))) {
        voRes.setFail(voRes);
        voRes.setMessage("领域不能为空，请按照正确的样例填写");
        return voRes;
    }
    JSONArray darr = null;
    try {
        darr = JSON.parseArray(json.getString("domain"));
    } catch (Exception ex) {
        darr = null;
    }
    String lydef = "1,2,3,4,5,6,99";
    if (null != darr && 0 < darr.size()) {
        for (int i = 0; i < darr.size(); i++) {
            if (0 > lydef.indexOf((String) darr.get(i))) {
                voRes.setFail(voRes);
                voRes.setMessage("领域录入信息错误，请按照正确的样例填写");
                return voRes;
            }
        }
    } else {
        voRes.setFail(voRes);
        voRes.setMessage("领域不能为空，请按照正确的样例填写");
        return voRes;
    }
}
