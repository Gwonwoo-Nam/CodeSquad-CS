# 이미지 가져오기
FROM ubuntu/mysql

# 로케일 다운로드
RUN apt-get update
RUN apt-get -y upgrade
RUN apt-get install locales
RUN apt-get install -y language-pack-ko
RUN locale-gen ko_KR.utf8

# 로케일 설정 업데이트
ENV LANG ko_KR.utf8
ENV LANGUAGE ko_KR.utf
ENV LC_MESSAGES POSIX
RUN update-locale LANG=ko_KR.utf8 LC_MESSAGES=POSIX