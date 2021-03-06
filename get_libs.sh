mkdir -p jar
mkdir -p lib
mkdir -p get_libs
cd get_libs

if [ ! -e NeticaJ_Linux.zip ]; then
    wget http://norsys.com/downloads/NeticaJ_Linux.zip
    unzip NeticaJ_Linux.zip
fi

echo Copying Netica libs
if [ `uname -m` == x86_64 ]; then
    cp NeticaJ_*/bin/64_bit/NeticaJ.jar ../jar
    cp NeticaJ_*/bin/64_bit/lib* ../lib
else
    cp NeticaJ_*/bin/NeticaJ.jar ../jar
    cp NeticaJ_*/bin/lib* ../lib
fi
