// File generated by FlutterFire CLI.
// ignore_for_file: type=lint
import 'package:firebase_core/firebase_core.dart' show FirebaseOptions;
import 'package:flutter/foundation.dart'
    show defaultTargetPlatform, kIsWeb, TargetPlatform;

/// Default [FirebaseOptions] for use with your Firebase apps.
///
/// Example:
/// ```dart
/// import 'firebase_options.dart';
/// // ...
/// await Firebase.initializeApp(
///   options: DefaultFirebaseOptions.currentPlatform,
/// );
/// ```
class DefaultFirebaseOptions {
  static FirebaseOptions get currentPlatform {
    if (kIsWeb) {
      return web;
    }
    switch (defaultTargetPlatform) {
      case TargetPlatform.android:
        return android;
      case TargetPlatform.iOS:
        return ios;
      case TargetPlatform.macOS:
        return macos;
      case TargetPlatform.windows:
        return windows;
      case TargetPlatform.linux:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for linux - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      default:
        throw UnsupportedError(
          'DefaultFirebaseOptions are not supported for this platform.',
        );
    }
  }

  static const FirebaseOptions web = FirebaseOptions(
    apiKey: 'AIzaSyCR_fveeJhqrRId7elq2PSJgv3L9A_WNcg',
    appId: '1:648880196594:web:4bf626874709244ec9796b',
    messagingSenderId: '648880196594',
    projectId: 'examplemobile-1754a',
    authDomain: 'examplemobile-1754a.firebaseapp.com',
    storageBucket: 'examplemobile-1754a.firebasestorage.app',
    measurementId: 'G-476153N0KJ',
  );

  static const FirebaseOptions android = FirebaseOptions(
    apiKey: 'AIzaSyDNDObRGrcoY6jk3g36R0Nrb-1e9GkDE8Q',
    appId: '1:648880196594:android:e63d2adf67b3e78dc9796b',
    messagingSenderId: '648880196594',
    projectId: 'examplemobile-1754a',
    storageBucket: 'examplemobile-1754a.firebasestorage.app',
  );

  static const FirebaseOptions ios = FirebaseOptions(
    apiKey: 'AIzaSyDiUE7yFSDeIBGbYI39sExBxrHtVdxZuKc',
    appId: '1:648880196594:ios:8f17fb10d2362e3bc9796b',
    messagingSenderId: '648880196594',
    projectId: 'examplemobile-1754a',
    storageBucket: 'examplemobile-1754a.firebasestorage.app',
    iosBundleId: 'com.example.flutterApplication1',
  );

  static const FirebaseOptions macos = FirebaseOptions(
    apiKey: 'AIzaSyDiUE7yFSDeIBGbYI39sExBxrHtVdxZuKc',
    appId: '1:648880196594:ios:8f17fb10d2362e3bc9796b',
    messagingSenderId: '648880196594',
    projectId: 'examplemobile-1754a',
    storageBucket: 'examplemobile-1754a.firebasestorage.app',
    iosBundleId: 'com.example.flutterApplication1',
  );

  static const FirebaseOptions windows = FirebaseOptions(
    apiKey: 'AIzaSyCR_fveeJhqrRId7elq2PSJgv3L9A_WNcg',
    appId: '1:648880196594:web:952dfd264202653dc9796b',
    messagingSenderId: '648880196594',
    projectId: 'examplemobile-1754a',
    authDomain: 'examplemobile-1754a.firebaseapp.com',
    storageBucket: 'examplemobile-1754a.firebasestorage.app',
    measurementId: 'G-R8N0RXREFK',
  );
}
