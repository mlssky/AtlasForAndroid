/**
 *  OpenAtlasForAndroid Project
The MIT License (MIT) Copyright (OpenAtlasForAndroid) 2015 Bunny Blue,achellies

Permission is hereby granted, free of charge, to any person obtaining a copy of this software
and associated documentation files (the "Software"), to deal in the Software 
without restriction, including without limitation the rights to use, copy, modify, 
merge, publish, distribute, sublicense, and/or sell copies of the Software, and to 
permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies 
or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
@author BunnyBlue
 * **/
package com.openAtlas.runtime;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;


public class PackageLite {
	private static final String XMLDISABLECOMPONENT_SSO_ALIPAY_AUTHENTICATION_SERVICE = "blue.stack.openAtlas.android.sso.internal.AlipayAuthenticationService";
	private static final String XMLDISABLECOMPONENT_SSO_AUTHENTICATION_SERVICE = "blue.stack.openAtlas.android.sso.internal.AuthenticationService";

	public String applicationClassName;
	public int applicationDescription;
	public int applicationIcon;
	public int applicationLabel;
	public final Set<String> components;
	public final Set<String> disableComponents;
	public Bundle metaData;
	public String packageName;
	public int versionCode;
	public String versionName;
	static String tag="PackageLite";


	PackageLite() {
		this.components = new HashSet<String>();
		this.disableComponents = new HashSet<String>();
	}

	public static PackageLite parse(File file) {


		XmlResourceParser openXmlResourceParser = null;
		try {
			AssetManager assetManager = AssetManager.class
					.newInstance();
			Method addAssetPath = AssetManager.class.getMethod("addAssetPath", new Class[]{String.class});
			int intValue = (Integer) addAssetPath.invoke(assetManager, file.getAbsolutePath());

			if (intValue != 0) {
				openXmlResourceParser = assetManager.openXmlResourceParser(
						intValue, "AndroidManifest.xml");
			} else {
				openXmlResourceParser = assetManager.openXmlResourceParser(
						intValue, "AndroidManifest.xml");
			}
			if (openXmlResourceParser != null) {
				try {
					PackageLite parse = parse(openXmlResourceParser);
					if (parse == null) {
						parse = new PackageLite();
					}

					openXmlResourceParser.close();
					return parse;
				} catch (Exception e) {
					e.printStackTrace();

					try {

						if (openXmlResourceParser != null) {
							openXmlResourceParser.close();
						}
						return null;
					} catch (Throwable th) {

						if (openXmlResourceParser != null) {
							openXmlResourceParser.close();
						}
						throw th;
					}
				}
			}
			return null;
		} catch (Exception e) {

			openXmlResourceParser = null;

			return null;
		} catch (Throwable eThrowable) {

			if (openXmlResourceParser != null) {
				openXmlResourceParser.close();
			}

		}
		return null;
	}

	protected static PackageLite parse(XmlResourceParser xmlResourceParser)throws Exception {
		int currentTag=xmlResourceParser.next();
		PackageLite mPackageLite = new PackageLite();
		while (currentTag!=XmlPullParser.END_DOCUMENT) {
			switch (currentTag) {
			case XmlPullParser.START_DOCUMENT:	

				break;
			case XmlPullParser.START_TAG:
				
				if (xmlResourceParser.getName().equals("manifest")) {
					parserManifestAttribute(xmlResourceParser, mPackageLite);
				}

				if (xmlResourceParser.getName().equals("application")) {
					if (!parseApplication(mPackageLite, (xmlResourceParser),
							(xmlResourceParser))) {
						return null;
					}

					return mPackageLite;
				}
				break;
			case XmlPullParser.END_DOCUMENT:
				xmlResourceParser.close();
				break;

			case XmlPullParser.END_TAG:
				
				break;
			default:
				break;
			}
		
			currentTag=xmlResourceParser.next();
		}
//TODO  if code is code  delete next version
		//	
		//		do {
		//			index = xmlResourceParser.next();
		//			if (index == startTag) {
		//				break;
		//			}
		//		} while (index != XmlPullParser.END_DOCUMENT);
		//
		//		if (index != startTag) {
		//			//PackageLite.log.error("No start tag found");
		//			mPackageLite=null;
		//		} else if (!xmlResourceParser.getName().equals("manifest")) {
		//			//PackageLite.log.error("No <manifest> tag");
		//			mPackageLite=null;
		//		} else {
		//			mPackageLite.packageName = ((AttributeSet) xmlResourceParser).getAttributeValue(null,
		//					"package");
		//			if (mPackageLite.packageName != null && mPackageLite.packageName.length() != 0) {
		//				index = 0;
		//
		//			} else {
		//				//	PackageLite.log.error("<manifest> does not specify package");
		//				return null;
		//			}
		//
		//			for (int i = 0; i <((AttributeSet) xmlResourceParser).getAttributeCount(); i++) {
		//				String value = ((AttributeSet) xmlResourceParser).getAttributeName(i);
		//				if (value.equals("versionCode")) {
		//					mPackageLite.versionCode = ((AttributeSet) xmlResourceParser)
		//							.getAttributeIntValue(i, 0);
		//
		//				} else if (value.equals("versionName")) {
		//					mPackageLite.versionName = ((AttributeSet) xmlResourceParser)
		//							.getAttributeValue(i);
		//
		//				}
		//
		//			}
		//
		//			index = xmlResourceParser.getDepth() + 1;
		//			while (true) {
		//				int v1 = xmlResourceParser.next();
		//				System.out.println(xmlResourceParser.getName());
		//				if (v1 != XmlPullParser.END_DOCUMENT) {
		//					if (xmlResourceParser.getName().equals("application")) {
		//						if (!PackageLite
		//								.parseApplication(mPackageLite, (xmlResourceParser),
		//										(xmlResourceParser))) {
		//							return null;
		//						}
		//
		//						return mPackageLite;
		//					}
		//
		//					if (v1 == endTag && xmlResourceParser.getDepth() < index) {
		//						break;
		//					}
		//
		//					if (v1 == endTag) {
		//						continue;
		//					}
		//
		//					if (v1 == 4) {
		//						continue;
		//					}
		//
		//					PackageLite.skipCurrentTag((xmlResourceParser));
		//					continue;
		//				}
		//
		//				break;
		//			}
		//
		//
		//		}

		return mPackageLite;

	}

	/**
	 * parser ManifestAttribute such package name and so on
	 * @param xmlResourceParser
	 * @param mPackageLite
	 */
	private static void parserManifestAttribute(
			XmlResourceParser xmlResourceParser, PackageLite mPackageLite) {
		mPackageLite.packageName = ((AttributeSet) xmlResourceParser).getAttributeValue(null,"package");
		if (TextUtils.isEmpty(mPackageLite.packageName)) {
			Log.e("tag", "packageName is null");
		}

		mPackageLite.versionCode = ((AttributeSet) xmlResourceParser).getAttributeIntValue(null, "versionCode", 0);
		mPackageLite.versionName = ((AttributeSet) xmlResourceParser)
				.getAttributeValue(null,
						"versionName");
		//					for (int i = 0; i <((AttributeSet) xmlResourceParser).getAttributeCount(); i++) {
		//						String value = ((AttributeSet) xmlResourceParser).getAttributeName(i);
		//						if (value.equals("versionCode")) {
		//							mPackageLite.versionCode = ((AttributeSet) xmlResourceParser)
		//									.getAttributeIntValue(i, 0);
		//
		//						} else if (value.equals("versionName")) {
		//							mPackageLite.versionName = ((AttributeSet) xmlResourceParser)
		//									.getAttributeValue(i);
		//
		//						}
		//
		//					}
	}



	private static boolean parseApplication(PackageLite packageLite,
			XmlPullParser xmlPullParser, AttributeSet attributeSet)
					throws Exception {
		int i;
		String str = packageLite.packageName;
		for (i = 0; i < attributeSet.getAttributeCount(); i++) {
			String attributeName = attributeSet.getAttributeName(i);
			if (attributeName.equals("name")) {
				packageLite.applicationClassName = buildClassName(str,
						attributeSet.getAttributeValue(i));
			} else if (attributeName.equals("icon")) {
				packageLite.applicationIcon = attributeSet
						.getAttributeResourceValue(i, 0);
			} else if (attributeName.equals("label")) {
				packageLite.applicationLabel = attributeSet
						.getAttributeResourceValue(i, 0);
			} else if (attributeName.equals("description")) {
				packageLite.applicationDescription = attributeSet
						.getAttributeResourceValue(i, 0);
			}
		}



		final int innerDepth = xmlPullParser.getDepth();

		int type;
		while ((type = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT
				&& (type != XmlPullParser.END_TAG || xmlPullParser.getDepth() > innerDepth)) {
			if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
				continue;
			}

			String tagName = xmlPullParser.getName();
			if (tagName.equals("activity")) {

				parseComponentData(packageLite, xmlPullParser,
						attributeSet, false);

			} else if (tagName.equals("receiver")) {

				parseComponentData(packageLite, xmlPullParser,
						attributeSet, true);

			} else if (tagName.equals("service")) {

				parseComponentData(packageLite, xmlPullParser,
						attributeSet, true);

			} else if (tagName.equals("provider")) {

				parseComponentData(packageLite, xmlPullParser,
						attributeSet, false);

			} else if (tagName.equals("activity-alias")) {
			} else if (xmlPullParser.getName().equals("meta-data")) {

				packageLite.metaData = parseMetaData(xmlPullParser,
						attributeSet, packageLite.metaData);


			} else if (tagName.equals("uses-library")) {
			} else if (tagName.equals("uses-package")) {
			} else {
			}
		}

		return true;
	}

	private static Bundle parseMetaData(XmlPullParser xmlPullParser,
			AttributeSet attributeSet, Bundle bundle)
					throws XmlPullParserException, IOException {
		int i = 0;
		if (bundle == null) {
			bundle = new Bundle();
		}
		String str = null;
		String str2 = null;
		int i2 = 0;
		while (i < attributeSet.getAttributeCount()) {
			String attributeName = attributeSet.getAttributeName(i);
			if (attributeName.equals("name")) {
				str2 = attributeSet.getAttributeValue(i);
				i2++;
			} else if (attributeName.equals("value")) {
				str = attributeSet.getAttributeValue(i);
				i2++;
			}
			if (i2 >= 2) {
				break;
			}
			i++;
		}
		if (!(str2 == null || str == null)) {
			bundle.putString(str2, str);
		}
		return bundle;
	}

	private static String buildClassName(String str, CharSequence charSequence) {
		if (charSequence == null || charSequence.length() <= 0) {
			System.out.println("Empty class name in package " + str);
			return null;
		}
		String obj = charSequence.toString();
		char charAt = obj.charAt(0);
		if (charAt == '.') {
			return (str + obj).intern();
		}
		if (obj.indexOf(46) < 0) {
			StringBuilder stringBuilder = new StringBuilder(str);
			stringBuilder.append('.');
			stringBuilder.append(obj);
			return stringBuilder.toString().intern();
		} else if (charAt >= 'a' && charAt <= 'z') {
			return obj.intern();
		} else {
			System.out.println("Bad class name " + obj + " in package " + str);
			return null;
		}
	}
			@SuppressWarnings("unused")
			@Deprecated
	private static void skipCurrentTag(XmlPullParser xmlPullParser)
			throws XmlPullParserException, IOException {
		int depth = xmlPullParser.getDepth();
		while (true) {
			int next = xmlPullParser.next();
			if (next == XmlPullParser.END_DOCUMENT) {
				return;
			}
			if (next == XmlPullParser.END_TAG && xmlPullParser.getDepth() <= depth) {
				return;
			}
		}
	}

	private static void parseComponentData(PackageLite packageLite,
			XmlPullParser xmlPullParser, AttributeSet attributeSet, boolean isDisable)
					throws XmlPullParserException {

		String pkgName = packageLite.packageName;
		for (int index = 0; index <attributeSet.getAttributeCount(); index++) {
			if (attributeSet.getAttributeName(index).equals("name")) {
				String mComponentName = attributeSet.getAttributeValue(index);
				if (mComponentName.startsWith(".")) {
					mComponentName = pkgName.concat(mComponentName);
				}
				packageLite.components.add(mComponentName);
				if (isDisable
						&& !(TextUtils
								.equals(mComponentName,
										XMLDISABLECOMPONENT_SSO_ALIPAY_AUTHENTICATION_SERVICE) && TextUtils
										.equals(mComponentName,
												XMLDISABLECOMPONENT_SSO_AUTHENTICATION_SERVICE))) {
					packageLite.disableComponents.add(mComponentName);
				}

			}
		}

	}
}
