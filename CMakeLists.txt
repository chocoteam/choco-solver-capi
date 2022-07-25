cmake_minimum_required(VERSION 3.10)

project(choco-solver-capi)

include(GNUInstallDirs)

SET(CHOCO_LIBRARY "choco-solver-capi${CMAKE_SHARED_LIBRARY_SUFFIX}")

install(
    FILES
    ${CMAKE_BINARY_DIR}/target/choco-solver-capi.h 
    ${CMAKE_BINARY_DIR}/target/choco-solver-capi_dynamic.h
    ${CMAKE_BINARY_DIR}/target/graal_isolate.h
    ${CMAKE_BINARY_DIR}/target/graal_isolate_dynamic.h
    DESTINATION 
    ${CMAKE_INSTALL_INCLUDEDIR}/choco-solver-capi
)

install(
    FILES
    ${CMAKE_BINARY_DIR}/target/${CHOCO_LIBRARY}
    DESTINATION        
    ${CMAKE_INSTALL_LIBDIR}
)