import fetch from '@/utils/request';

export function saleDayList(data) {
  return fetch({
    url: '/api/sale_day',
    method: 'post',
    data
  });
}
export function sale_day(data) {
  return fetch({
    url: '/api/sale_day',
    method: 'post',
    data
  });
}
export function sale_today(data) {
  return fetch({
    url: '/api/sale_today',
    method: 'post',
    data
  });
}

export function sale_month(data) {
  return fetch({
    url: '/api/sale_month',
    method: 'post',
    data
  });
}


export function saleDayHistory(params) {
  return fetch({
    url: '/api/sale_day_history',
    method: 'post',
    params: { params }
  });
}

export function saleDaytime(data) {
  return fetch({
    url: '/api/sale_day_query',
    method: 'post',
    data
  });
}
export function dep_list(data) {
  return fetch({
    url: '/api/dep_list',
    method: 'post',
    data
  });
}
export function saleDaycompany(data) {
  return fetch({
    url: '/api/sale_query_dep',
    method: 'post',
    data
  });
}
export function saleDayproject(data) {
  return fetch({
    url: '/api/project_query',
    method: 'post',
    data
  });
}
