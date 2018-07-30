import request from '@/utils/request'


export function registers(data) {
  return request({
    url: '/xtcx/user/register',
    method: 'post',
    data
  })
}
export function login(data) {
  return request({
    //url: '/user/login',
    url: '/xtcx/user/login',
    method: 'post',
    data
  });
}

export function getUserDetail(activateId) {
  return request({
    url: '/xtcx/lib/getUserDetail?isDraft=false',
    method: 'get'
  })
}

export function setUserDetail(data) {
  return request({
    url: '/xtcx/lib/setUserDetail?isDraft=false',
    method: 'post',
    data
  });
}
export function getBaseUserDetail() {
  return request({
    url: '/xtcx/user',
    method: 'get'
  })
}

export function setBaseUserDetail(data) {
  return request({
    url: '/xtcx/user',
    method: 'put',
    data
  });
}

export function getActivate(activateId) {
  return request({
    url: '/xtcx/user/activate?=activateId'+activateId,
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
    method: 'post'
  })
}
