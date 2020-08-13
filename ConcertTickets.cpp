#include <iostream>
#include <algorithm>
#include <map>

using namespace std;
using ll = long long;

int main() {
    ll n, m; cin >> n >> m;
    ll a[n], x;
    map<ll, ll> cust;
    for(ll i=0; i<n; i++) cin >> a[i];
    for(ll i=0; i<m; i++) {
        cin >> x;
        cust[i] = x;
    }
    sort(a, a+n);
    sort(cust.begin(), cust.end(), [](pair<ll, ll>& a, pair<ll, ll>& b) {
         return a.second < b.second;});
    ll i=0;
    map<ll, ll>::iterator j = cust.begin();
    while(i < n && j != cust.end()) {
        if(a[i] <= j->second) {
            j->second = min(j->second, a[i]);
            i++, j++;
        }
        else {
            j->second = -1;
            j++;
        }
    }
    while(j != cust.end()) {
        j->second = -1;
        j++;
    }

    sort(cust.begin(), cust.end(), [](pair<ll, ll>& a, pair<ll, ll>& b) {
         return a.first < b.first;});

    for(auto it: cust) cout << it.second << endl;
}
