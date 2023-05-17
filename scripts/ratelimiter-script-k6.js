import http from 'k6/http';

import { sleep } from 'k6';


export const options = {
    vus: 15,
    duration: '30s'
  };

export default function () {
  http.get('http://localhost:8080/rate-limiter');
  sleep(1);
}