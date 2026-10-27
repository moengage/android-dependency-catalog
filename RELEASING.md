# Release Process

A release runs as three workflows, one after the other. Each one starts the next. Each one is safe to
run again, because it skips whatever is already done.

| # | Workflow | What it does |
|---|---|---|
| 1 | **Release** (`release.yml`) | Bumps the BOM and catalog versions from their changelogs, dates the changelogs, commits and pushes to `master`, and pushes the tags `bom-v…` and `catalog-v…`. It publishes nothing. |
| 2 | **Publish** (`publish.yml`) | Uploads the BOM and catalog to Maven Central. Skips any version that is already there. Does not run until step 1 has created the tag. |
| 3 | **Post Release** (`post_release.yml`) | Creates the GitHub Releases and posts the Slack message. |

Normally the SDK's *Version Update For Catalog Repos* stage starts step 1 after an SDK release. To start
it by hand: **Actions → Release → Run workflow**, with the release ticket and the release notes link.

## If a step fails

| Failed in | What has happened | What to do |
|---|---|---|
| Release | Nothing is published | Fix the cause and run Release again. A commit or tag that was already pushed is found and not repeated. |
| Publish | Versions are committed and tagged. One of the two may be uploaded. | Fix the cause and run Publish again. Anything already on Maven Central is skipped. |
| Publish, and the fix needs a change in this repo (for example a bug in a build file) | The version is committed and tagged, but not published | Fix it on `master`, delete the tag (`git push origin :refs/tags/catalog-v<version>`), and run Release again. It tags the fixed commit with the same version, and the next steps follow. |
| A version was published with a mistake | It is on Maven Central for good and cannot be changed | Release the next version: add a `# Release Date` / `## Release Version` block with a `[patch]` entry at the top of the changelog, and run Release. |
| Post Release | Both are published | Run Post Release again. GitHub Releases that already exist are skipped. If only the Slack message failed, tick *Send Slack even if the GitHub releases already exist?* |
