FROM ubuntu:latest

ENV GMP_VERSION=6.0.0
ENV GNU_COBOL=1.1

RUN apt-get update
RUN apt-get -y install libtool lib32ncurses5 lib32ncurses5-dev build-essential && \
    apt-get -y install -y m4 libdb-dev libdbi-dev libtool libltdl-dev wget vim 

RUN mkdir /src

RUN cd /src && wget https://ftp.gnu.org/gnu/gmp/gmp-${GMP_VERSION}a.tar.xz && \
    tar xf gmp-${GMP_VERSION}a.tar.xz && \
    cd gmp-${GMP_VERSION} && ./configure ; make ; make check ; make install

RUN cd /src && wget http://sourceforge.net/projects/open-cobol/files/gnu-cobol/${GNU_COBOL}/gnu-cobol-${GNU_COBOL}.tar.gz && \
    tar xvzf gnu-cobol-${GNU_COBOL}.tar.gz && \
    cd gnu-cobol-${GNU_COBOL} && ./configure ; make ; make check ; make install

ENV LD_LIBRARY_PATH /usr/local/lib:$LD_LIBRARY_PATH

CMD "./src/helloworld-exe"
