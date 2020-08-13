#include <iostream>
#include <cmath>

using namespace std;

int main() {
    int t, sx, sy, ex, ey;
    cin >> t >> sx >> sy >> ex >> ey;
    string s;
    cin >> s;

    bool found = false;
    int nextx, nexty, time=0;
    double dist = sqrt(pow(ex-sx, 2) + pow(ey-sy, 2));
    int currx = sx, curry = sy;
    for(char c: s) {
        if(c == 'E') nextx = currx+1, nexty = curry;
        else if(c == 'W') nextx = currx-1, nexty = curry;
        else if(c == 'N') nextx = currx, nexty = curry+1;
        else nextx = currx, nexty = curry-1;

        double newD = sqrt(pow(nextx-ex, 2) + pow(nexty-ey, 2));
        if(newD < dist) {
            dist = newD;
            currx = nextx, curry = nexty;
        }
        time++;
        if(currx == ex && curry == ey) {
            found = true;
            break;
        }
    }
    if(found) cout << time;
    else cout << -1;
    return 0;
}
