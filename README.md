# FriendlyChat-Test

This repository contains code for the FriendlyChat-Test project based on the project placed in the [Firebase in a Weekend: Android by Google](https://www.udacity.com/course/firebase-in-a-weekend-by-google-android--ud0352) Udacity course.

This version of the application adds additional app tracking via Firebase, Appsflyer, and Google Analytics SDK

This version also stiches sessions between AMP pages and app views on Google Analytics properties.

## Overview

FriendlyChat-Test is an app that allows users to send and receive text in realtime across platforms. It is also designed to receive Android App Links from www.codellect.org. 

A user session can start on this website via a visit to a normal web-page or an AMP page. While clicking on a link to the AMP page from Google Search would always open the page in the [Google AMP viewer](https://support.google.com/websearch/answer/7220196), any further clicks on a codellect.org link will lead user to FriendlyChat-Test app. Clicking on a non-AMP codellect.org from Google Search would also lead the user directly to FriendlyChat-Test app.

While sessions (and other interactions) on the website (www.codellect.org) are reported to the GA property ÒUA-101537658-1Ó, the sessions (and other interactions) on FriendlyChat-Test are reported to GA property ÒUA-126353338-4Ó. 

However, FriendlyChat-Test is also designed to stitch sessions between traffic that originates on an AMP page and lands inside the app. Note that FriendlyChat-Test does not stitch sessions between traffic that originates on non-AMP mobile pages and lands inside the app- since such use cases are limited. 

Sessions stitching between App and AMP traffic is achieved by utilizing a combination of [Google AMP Client ID API]( https://support.google.com/analytics/answer/7486764?hl=en) and [AMP Linker]( https://amphtml.wordpress.com/2018/09/17/measuring-user-journeys-across-the-amp-cache-and-your-website/). Check the setup section for details

## Setup

Requires Firebase, Appslfyer, Google Analytics and an AMP property.

## License
See [LICENSE](LICENSE)



