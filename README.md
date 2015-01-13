# Unity Charge Status

An Android plugin for Unity to check whether or not the device is charging (not running on battery).

Usage example:

        using UnityEngine;

        public class UsageExample : MonoBehaviour
        {

            private AndroidJavaObject chargeStatus = null;

            void Update()
            {

                #if (UNITY_ANDROID) && (!UNITY_EDITOR)
                if (chargeStatus == null)
                {
                    AndroidJavaObject activityContext = null;
                    using (AndroidJavaClass activityClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
                    {
                        activityContext = activityClass.GetStatic<AndroidJavaObject>("currentActivity");
                    }
                    using (AndroidJavaClass pluginClass = new AndroidJavaClass("com.ambethia.unitychargestatus.ChargeStatus"))
                    {
                        if (pluginClass != null)
                        {
                            chargeStatus = pluginClass.CallStatic<AndroidJavaObject>("instance");
                            chargeStatus.Call("setContext", activityContext);
                        }
                    }
                } else
                {
                    bool isCharging = chargeStatus.Call<bool>("isCharging");
                    if (isCharging)
                    {
                        Screen.sleepTimeout = SleepTimeout.NeverSleep;
                    } else
                    {
                        Screen.sleepTimeout = 5;
                    }
                }
                #endif
            }
        }
