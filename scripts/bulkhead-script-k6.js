import http from 'k6/http';


export const options = {
    discardResponseBodies: true,
    scenarios: {
        shortRequest: {
            executor: 'per-vu-iterations',
            exec: 'shortRequests',
            vus: 50,
            iterations: 50,
            maxDuration: '2.5m'
        },
        longRequest: {
            executor: 'per-vu-iterations',
            exec: 'longRequests',
            vus: 50,
            iterations: 50,
            maxDuration: '2.5m'
        }
    },
  };

export function shortRequests() {
    http.get('http://localhost:8080/bulkhead/short-request');
}

export function longRequests() {
  http.get('http://localhost:8080/bulkhead/long-request-with-bulkhead');
}