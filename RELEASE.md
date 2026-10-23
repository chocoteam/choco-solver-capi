# Releasing choco-solver-capi

A release is an annotated tag `choco-solver-capi-X.Y.Z` on a commit whose `pom.xml` has the
version `X.Y.Z`. Nothing is deployed: the native library is built by the projects that use it
(pychoco builds it in its own CI, at the commit its submodule points to).

1. Check that the [Build workflow](.github/workflows/build.yml) is green on `master`. If the
   choco-solver or GraalVM version changed since the last release, record a new PGO profile first
   (see [pgo/README.md](pgo/README.md)).

2. Set the release version and commit it:

   ```sh
   mvn versions:set -DnewVersion=X.Y.Z -DgenerateBackupPoms=false
   git commit -am "Release X.Y.Z"
   ```

3. Tag it. The first line of the tag message is the release title (`choco-solver-capi X.Y.Z`),
   the rest its notes: the bundled choco-solver version (the `choco-solver` dependency of
   `pom.xml`) and the changes since the previous tag
   (`git log --oneline choco-solver-capi-<previous>..HEAD`):

   ```sh
   git tag -a choco-solver-capi-X.Y.Z
   ```

4. Move back to a development version, then push both commits and the tag:

   ```sh
   mvn versions:set -DnewVersion=X.Y.(Z+1)-SNAPSHOT -DgenerateBackupPoms=false
   git commit -am "Prepare next development version"
   git push origin master choco-solver-capi-X.Y.Z
   ```

5. The tag push runs the Build workflow, whose `release` job turns the tag into a GitHub Release
   once the native library builds on the three OS. Check it on the Releases page; if the job
   failed, fix the cause and re-run it rather than moving the tag.

6. In pychoco, point the submodule to the tagged commit (not to the development version that
   follows it) and mention the update in `NEWS.md`:

   ```sh
   git -C choco-solver-capi checkout choco-solver-capi-X.Y.Z
   git add choco-solver-capi NEWS.md
   ```
