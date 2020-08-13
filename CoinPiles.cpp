#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int main() {
    ll t; cin >> t;
    while(t--) {
        ll x, y; cin >> x >> y;

        if(x < y) {
            ll t = x; x = y; y = t;
        }
        if(x > 2*y) cout << "NO\n" << endl;
        else {
            if((x + y)%3 == 0) cout << "YES\n";
            else cout << "NO\n";
        }
    }

        /*
        ll dp[x+1][y+1];
        for(ll i=0; i<=x; i++) dp[i][0] = 0LL;
        for(ll i=0; i<=y; i++) dp[0][i] = 0LL;
        dp[1][2] = 1; dp[2][1] = 1;
        for(ll i=1; i<=x; i++) {
            for(ll j=1; j<=y; j++) {
                ll t1=0, t2=0;
                if(i == 1 && j == 2 || i == 2 && j == 1) continue;
                if(i-1 >= 0 && j-2 >= 0) t1 = dp[i-1][j-2];
                if(i-2 >= 0 && j-1 >= 0) t2 = dp[i-2][j-1];
                dp[i][j] = max(t1, t2);
            }
        }
        if(dp[x][y] == 1) cout << "YES\n";
        else cout << "NO\n";
        */

    return 0;
}
