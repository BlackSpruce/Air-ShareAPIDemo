# Air-ShareAPIDemo
 
Hello Air-Share users. https://play.google.com/store/apps/details?id=com.blackspruce.gloo
In the latest version (1.25) of Air-Share I added an Intent based API which allows other App developers to leverage
Air-Share inside their apps. This provides a simple technique for App developers to share content between paired devices
programmatically without having to build the internet plumbing. 

The integration feature uses simple Android Intents. First send a ACTION_PICK Intent to “com.blackspruce.gloo.PickPairedDevice”. After the user selects a paired device, in onActivityResult, you will receive an Intent with two String extra's ; “deviceName” and “shareToken”. Next create a VIEW, SEND or SEND_MULTIPLE Intent, adding the “shareToken” extra to this new Intent. Send the Intent to “com.blackspruce.gloo.ReceiveLocalIntent” along with whatever data you wish to share. Follow Android Developer recommended techniques to build your Intent. The “shareToken” will direct your Intent to the user's chosen device and then will expire after the Intent is forwarded. View sample source code here. If you want to target the Intent to your own app on the share destination device, you can try implementing a custom mime type in the intent's setType method or use a custom URI Scheme. If your app has an Intent filter to match, then it should be the only choice to receive the Intent.

Any questions send me an email at the address on the Apps Google Play page,
BlackSpruce
