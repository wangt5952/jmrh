import request from '@/utils/request'

import {
  getToken,
} from '@/utils/auth'
let token= getToken()

export function getCategoryTree(data) {
  return request({
    url: '/xtcx/category/tree',
    method: 'post',
    data
  });
}

export function getLeafNodes(data) {
  return request({
    url: '/xtcx/category/getLeafNodes',
    method: 'get',
  });
}


export function putCategory(data) {//编辑栏目
  return request({
    url: '/xtcx/category',
    method: 'put',
    data
  });
}

export function postCategory(data) {
  return request({
    url: '/xtcx/category',
    method: 'post',
    data
  });
}

export function delcategory(data) {
  return request({
    url: '/xtcx/category?id='+data.id,
    method: 'delete'
  });
}




export function getExchanges(data) {
  return request({
    url: '/xtcx/exchanges/page?pageNum='+data.page+'&pageSize='+data.limit,
    method: 'post',
    data
  });
}
export function getExchangesC(id) {
  return request({
    url: '/xtcx/exchanges?id='+id,
    method: 'get'
  });
}

export function postExchanges(data) {
  return request({
    url: '/xtcx/exchanges/add',
    method: 'post',
    data
  });
}

export function submitExchanges(data) {
  return request({
    url: '/xtcx/exchanges/submit',
    method: 'post',
    data
  });
}

export function delexchanges(data) {
  return request({
    url: '/xtcx/exchanges?id='+data.id,
    method: 'delete',
    data
  });
}


export function rejectExchanges(data) {
  return request({
    url: '/xtcx/exchanges/reject',
    method: 'put',
    data
  });
}
export function checkExchanges(data) {
  return request({
    url: '/xtcx/exchanges/check',
    method: 'put',
    data
  });
}
export function onExchanges(data) {
  return request({
    url: '/xtcx/exchanges/on?id='+data.id,
    method: 'put'
  });
}
export function offExchanges(data) {
  return request({
    url: '/xtcx/exchanges/off',
    method: 'put',
    data
  });
}
export function topExchanges(data) {
  return request({
    url: '/xtcx/exchanges/top?id='+data.id+'&order='+data.order,
    method: 'put'
  });
}





export function getCategory(data) {
  return request({
    url: '/xtcx/category/content/page?pageNum='+data.page+'&pageSize='+data.limit,
    method: 'post',
    data
  });
}


export function postCategoryC(data) {
  return request({
    url: '/xtcx/category/content/add',
    method: 'post',
    data
  });
}

export function getCategoryC(id) {
  return request({
    url: '/xtcx/category/content?id='+id,
    method: 'get'
  });
}

export function delCategoryC(data) {
  return request({
    url: '/xtcx/category/content?id='+data.id,
    method: 'delete',
    data
  });
}



export function rejectCategory(data) {
  return request({
    url: '/xtcx/category/content/reject',
    method: 'put',
    data
  });
}
export function checkCategory(data) {
  return request({
    url: '/xtcx/category/content/check',
    method: 'put',
    data
  });
}
export function onCategory(data) {
  return request({
    url: '/xtcx/category/content/on?id='+data.id,
    method: 'put'
  });
}
export function offCategory(data) {
  return request({
    url: '/xtcx/category/content/off',
    method: 'put',
    data
  });
}
export function topCategory(data) {
  return request({
    url: '/xtcx/category/content/top?id='+data.id+'&order='+data.order,
    method: 'put'
  });
}



export function downloadExchanges(data) {
  return request({
    url: '/xtcx/exchanges/download?fileName='+data,
    method: 'get'
  });
}



export function uploadExchanges(data,num) {//活动上传附件以及富文本图片
  return request({
    url: '/xtcx/exchanges/upload?token='+token+'&fileType='+num,
    method: 'post',
    data
  });
}
export function uploadCategory(data,num) {//内容上传附件以及富文本图片
  return request({
    url: '/xtcx/category/content/upload?token='+token+'&fileType='+num,
    method: 'post',
    data
  });
}
export function uploadFile(data) {//封面
  return request({
    url: '/xtcx/file/upload?token='+token,
    method: 'post',
    data
  });
}


export function delFile(data) {
  return request({
    url: '/xtcx/file?id='+data,
    method: 'delete'
  });
}
