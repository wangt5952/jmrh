import request from '@/utils/request'


export function getexpert(data) {
  return request({
    url: '/xtcx/lib/expert/page?pageNum='+data.page+'&pageSize='+data.limit,
    method: 'post',
    data
  });
}

export function getcollege(data) {
  return request({
    url: '/xtcx/lib/college/page?pageNum='+data.page+'&pageSize='+data.limit,
    method: 'post',
    data
  });
}
export function getenterprise(data) {
  return request({
    url: '/xtcx/lib/enterprise/page?pageNum='+data.page+'&pageSize='+data.limit,
    method: 'post',
    data
  });
}
export function getservices(data) {
  return request({
    url: '/xtcx/lib/services/page?pageNum='+data.page+'&pageSize='+data.limit,
    method: 'post',
    data
  });
}
