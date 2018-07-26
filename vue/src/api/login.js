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
    url: '/xtcx/user/getUserDetail?userId=f2ef9bf2-8fce-11e8-8493-80ce62501f15',
    method: 'get'
  })
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
