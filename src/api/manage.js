import fetch from '@/utils/request';

export function loginbi(loginData) {
  return fetch({
    // url: '/bi/login?userName='+loginData.userName+'&password='+loginData.password,
    url: '/bi/login',
    method: 'post',
    data : loginData
  });
}

export function pageList(loginData) {
  return fetch({
    url: '/bi/foreign/pageList?page='+loginData.page+'&limit='+loginData.limit+'&objName='+loginData.objName,
    method: 'post'
  });
}

export function getqueryAll(loginData) {
  return fetch({
    url: '/bi/foreign/queryAll',
    method: 'get',
    loginData
  });
}

export function addInfo(loginData) {
  return fetch({
    url: '/bi/foreign/addInfo',
    method: 'post',
    data : loginData
  });
}

export function updateInfo(loginData) {
  return fetch({
    url: '/bi/foreign/updateInfo',
    method: 'post',
    data : loginData
  });
}
export function delInfo(id) {
  return fetch({
    url: '/bi/foreign/delInfo?id='+id,
    method: 'post'
  });
}
export function queryAllOrg() {
  return fetch({
    url: '/bi/orgStages/queryAllOrg',
    method: 'post'
  });
}
export function queryByOrgId(loginData) {
  return fetch({
    url: '/bi/orgStages/queryByOrgId?id='+loginData,
    method: 'post'
  });
}
