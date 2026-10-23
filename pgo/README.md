# Profile-guided optimizations

`build.sh` compiles the native library with `-O3 --pgo=pgo/choco.iprof.gz`: native-image
optimizes the code according to `choco.iprof.gz`, a profile of which branches and calls are hot.
Compared to the default `-O2`, it makes search 2 to 17 % faster depending on the model (the
smallest gains are for models whose time is spent in Python callbacks), and the library is
smaller.

The profile is a gzipped JSON file, independent of the OS. It is recorded from the pychoco test
suite, so that it covers a wide range of constraints and search strategies:

```sh
# from a pychoco checkout, with Oracle GraalVM 25 or later in $JAVA_HOME (Linux or macOS)
sh pgo/train.sh
```

That script builds an instrumented library (`sh build.sh instrument`), runs the tests with it,
writes the new profile to `pgo/choco.iprof.gz`, then rebuilds the optimized library.

Record a new profile when the code changes significantly, at least with each new choco-solver
version, and when moving to another GraalVM version. An outdated profile does not break the
build: native-image applies it on a best-effort basis, the gain simply decreases.
