# Release Process

- Merge all the releasing changes into `master` branch
- Update all updated module version in build.gradle.kts and update the changelog with header as
    - # Release Date
    - ## Release Version
- Merge the changes to master
- Run the `release` action to release the catalog by providing the required workflow input
- Notes:
  - Don't update catalog version which needs to be release, release workflow will auto update the version