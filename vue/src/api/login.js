import request from '@/utils/request'

export function isInLibs(data) {
  return request({
    url: '/lib/isInLibs',
    method: 'get',
    data
  });
}
export function activate(data) {
  return request({
    url: '/user/activate?activateId='+data,
    method: 'get',
    data
  });
}
export function sendEmailActivate(data) {
  return request({
    url: '/user/sendEmailActivate',
    method: 'post',
    data
  });
}

export function registers(data) {
  return request({
    url: '/user/register',
    method: 'post',
    data
  })
}
export function login(data) {
  return request({
    //url: '/user/login',
    url: '/user/login',
    method: 'post',
    data
  });
}

export function getUserDetail(activateId) {
  return request({
    url: '/lib/getUserDetail?isDraft=false',
    method: 'get'
  })
}

export function setUserDetail(data,flag) {
  return request({
    url: '/lib/setUserDetail?isDraft='+flag,
    method: 'post',
    data
  });
}
export function getBaseUserDetail() {
  return request({
    url: '/user',
    method: 'get'
  })
}

export function setBaseUserDetail(data) {
  return request({
    url: '/user',
    method: 'put',
    data
  });
}

export function getActivate(activateId) {
  return request({
    url: '/user/activate?=activateId'+activateId,
    method: 'get'
  })
}





export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: {
      token
    }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'get'
  })
}
