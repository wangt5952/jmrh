import request from '@/utils/request'


export function importLibExcel(data) {
  return request({
    url: '/lib/importLibExcel',
    method: 'post',
    data
  });
}
export function exportLib(data) {
  return request({
    url: '/lib/exportLib?objName='+data.objName+'&checkStatus=1&userType='+data.userType+'&creditLevel='+data.creditLevel+'&status='+data.status+'&token='+data.token,
    method: 'get',
  });
}

export function getexpert(data) {
  return request({
    url: '/lib/expert/page?pageNum='+data.page+'&pageSize='+data.limit+'&objName='+data.objName+'&status='+data.status+'&creditLevel='+data.creditLevel+'&checkStatus='+data.checkStatus,
    method: 'get',
  });
}

export function getcollege(data) {
  return request({
    url: '/lib/college/page?pageNum='+data.page+'&pageSize='+data.limit+'&objName='+data.objName+'&status='+data.status+'&creditLevel='+data.creditLevel+'&checkStatus='+data.checkStatus,
    method: 'get',

  });
}
export function getenterprise(data) {
  return request({
    url: '/lib/enterprise/page?pageNum='+data.page+'&pageSize='+data.limit+'&objName='+data.objName+'&status='+data.status+'&creditLevel='+data.creditLevel+'&checkStatus='+data.checkStatus,
    method: 'get',

  });
}
export function getservices(data) {
  return request({
    url: '/lib/services/page?pageNum='+data.page+'&pageSize='+data.limit+'&objName='+data.objName+'&status='+data.status+'&creditLevel='+data.creditLevel+'&checkStatus='+data.checkStatus,
    method: 'get',

  });
}
export function getresults(data) {
  return request({
    url: '/lib/results/page?pageNum='+data.page+'&pageSize='+data.limit+'&objName='+data.objName+'&status='+data.status+'&creditLevel='+data.creditLevel+'&checkStatus='+data.checkStatus,
    method: 'get',

  });
}
export function getrequirement(data) {
  return request({
    url: '/lib/requirement/page?pageNum='+data.page+'&pageSize='+data.limit+'&objName='+data.objName+'&status='+data.status+'&creditLevel='+data.creditLevel+'&checkStatus='+data.checkStatus,
    method: 'get',

  });
}


export function addpublish(data) {
  return request({
    url: '/lib/publish',
    method: 'post',
    data
  });
}

export function addLib(data) {
  return request({
    url: '/lib/addLib',
    method: 'post',
    data
  });
}
export function delLib(data) {
  return request({
    url: '/lib/delLib?id='+data.id+'&type='+data.form.formType,
    method: 'delete',
    data
  });
}

export function updateLevel(data,type) {//信用
  return request({
    url: '/lib/updateLibCreditLevel?id='+data.id+'&type='+type+'&creditLevel='+data.creditLevel,
    method: 'put',
    data
  });
}

export function rejectUserDetail(data) {//单个
  return request({
    url: '/lib/rejectUserDetail',
    method: 'post',
    data
  });
}



export function PLrejectUserDetail(data) {//批量审核 通过 单个审核通过
  return request({
    url: '/lib/checkUserDetails',
    method: 'post',
    data
  });
}
export function PLoffUserDetails(data,type) {//批量下架
  return request({
    url: '/lib/offUserDetails?type='+type,
    method: 'put',
    data
  });
}

export function PLonUserDetails(data,type) {//批量上架
  return request({
    url: '/lib/onUserDetails?type='+type,
    method: 'put',
    data
  });
}
export function onUserDetail(data) {//上架
  return request({
    url: '/lib/onUserDetail?id='+data.id+'&type='+data.type,
    method: 'put',
    data
  });
}
export function offUserDetail(data) {//下架
  return request({
    url: '/lib/offUserDetail?id='+data.id+'&type='+data.type,
    method: 'put',
    data
  });
}

export function downloadfileName() {//批量审核 通过 单个审核通过
  return request({
    url: '/lib/download?fileName=3-信息报送承诺书.doc',
    method: 'get'
  });
}

export function libupload(data) {//
  return request({
    url: '/lib/upload',
    method: 'post',
    data
  });
  }
export function libupload2(data) {//
  debugger
  return request({
    url: '/lib/upload2',
    method: 'post',
    data
  });
}
