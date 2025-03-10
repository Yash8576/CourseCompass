Course Compass

Course Compass is an Android app designed to help students explore different academic majors. It provides insights into various fields of study, including career opportunities, growth potential, and key aspects of each discipline. The app features an interactive user interface with both portrait and landscape support.

Features:

List of Majors: Uses a ListFragment to display a list of available majors.

Detailed View: Displays details of the selected major in a PortraitFragment when in portrait mode.

Dual-Pane Landscape View: In landscape mode, utilizes a FragmentContainerView to display:

A ListFragment on the left side.

A details fragment on the right side when a major is selected.

Resource Files:

13 images stored in res/drawable/.

6 text files stored in res/raw/ for detailed descriptions.

Installation:

Clone the repository:

git clone https://github.com/yourusername/CourseCompass.git

Open the project in Android Studio.

Build and run the app on an emulator or physical device.

Usage:

Launch the app and explore the list of available majors.

Tap on a major in portrait mode to view its details.

Rotate the device to landscape mode to see a split view with the list on the left and details on the right.

Technologies Used:

Java/Kotlin

Android Fragments & FragmentContainerView

Flutter

XML for UI Design

Screenshots:

Splash screen:

![Screenshot_1741578406](https://github.com/user-attachments/assets/484549fa-357d-4c35-ac83-ef927f5fd891)

choices for majors(select one to dive into details):

![Screenshot_1741578410](https://github.com/user-attachments/assets/6e4a73a9-d4ba-46fc-96d4-048791145496)

If the selection is computer science it details in portrait view:

![Screenshot_1741578416](https://github.com/user-attachments/assets/5d9295c3-8e4b-4493-abca-9d5a9a37d014)

If the selection is computer science it details in landscape view:

![Screenshot_1741578456](https://github.com/user-attachments/assets/3303386a-f5fe-485b-8a22-239b813ca817)


License

This project is licensed under the MIT License - see the LICENSE file for details.
