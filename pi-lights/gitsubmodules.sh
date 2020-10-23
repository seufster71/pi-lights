ECHO getting submodules

cd ~/git/pi-lights/pi-lights
git submodule init
git submodule update
git submodule foreach git checkout master
git submodule foreach git pull origin master