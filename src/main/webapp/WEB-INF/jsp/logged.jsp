<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Zarządzaj</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="../../assets/img/favicon.png" rel="icon">
    <link href="../../assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="../../assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="../../assets/vendor/aos/aos.css" rel="stylesheet">
    <link href="../../assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="../../assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
    <link href="../../assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="../../assets/css/style.css" rel="stylesheet">

    <!-- =======================================================
    * Template Name: FlexStart - v1.7.0
    * Template URL: https://bootstrapmade.com/flexstart-bootstrap-startup-template/
    * Author: BootstrapMade.com
    * License: https://bootstrapmade.com/license/
    ======================================================== -->
</head>

<body>

<!-- ======= Header ======= -->
<header id="header" class="header fixed-top">
    <div class="container-fluid container-xl d-flex align-items-center justify-content-between">

        <a href="/" class="logo d-flex align-items-center">
            <img src="../../assets/img/logo.png" alt="">
            <span>${loggedTeacher.firstName}</span>
        </a>

        <nav id="navbar" class="navbar">
            <ul>
                <li><a class="nav-link scrollto" href="/logged/allnotes">Twoje notatki</a></li>
                <li><a class="nav-link scrollto" href="/logged/allcourses">Przedmioty</a></li>
                <li><a class="nav-link scrollto" href="/logged/showgroupstochoose">Klasy</a></li>
                <li><a class="nav-link scrollto" href="/logged/allstudents">Uczniowie</a></li>
                <li><a class="getstarted scrollto" href="/logout">Wyloguj</a></li>
            </ul>
            <i class="bi bi-list mobile-nav-toggle"></i>
        </nav><!-- .navbar -->

    </div>
</header><!-- End Header -->

<main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section class="breadcrumbs">
        <div class="container">
            <h2>Zarządzaj swoimi uczniami</h2>
            <c:if test="${empty currentCourse}">
                <ol>
                    <li><a href="/logged/coursestochoose">Wybierz przedmiot</a></li>
                    <li><a href="/logged">Wybierz klasę</a></li>
                    <li><a href="/logged">Wybierz ucznia</a></li>
                </ol>
            </c:if>
            <c:if test="${empty currentGroup && not empty currentCourse}">
                <ol>
                    <li><a href="/logged/coursestochoose">Zmień przedmiot</a></li>
                    <li><a href="/logged/grouptochoose">Wybierz klasę</a></li>
                    <li><a href="/logged">Wybierz ucznia</a></li>
                </ol>
            </c:if>
            <c:if test="${not empty currentGroup && not empty currentCourse}">
                <ol>
                    <li><a href="/logged/coursestochoose">Zmień przedmiot</a></li>
                    <li><a href="/logged/grouptochoose">Zmień klasę</a></li>
                    <li><a href="/logged">Wybierz ucznia</a></li>
                </ol>
            </c:if>
        </div>
    </section><!-- End Breadcrumbs -->

    <section class="inner-page">
        <div class="container">
            <c:if test="${empty currentCourse}">
                <!-- ======= Features Section ======= -->
                <section id="features" class="features">

                    <div class="container" data-aos="fade-up">

                        <header class="section-header">
                            <p>Twoje przedmioty</p>
                        </header>

                        <div class="row">

                            <div class="col-lg-6 mt-5 mt-lg-0 d-flex">
                                <div class="row align-self-center gy-4">
                                    <c:if test="${teacherCourses.size() == 0}">
                                        Nie prowadzisz jeszcze żadnych kursów
                                        <a href="/logged/avilablecourses">Dodaj Przedmiot</a>
                                    </c:if>
                                    <c:if test="${teacherCourses.size() != 0}">
                                        <c:forEach var = "course" items="${teacherCourses}">
                                            <div class="col-md-6" data-aos="zoom-out" data-aos-delay="700">
                                                <div class="feature-box d-flex align-items-center">
                                                    <a href="/logged/choosecurrentcourse/${course.id}">
                                                        <h3>${course.name}</h3>
                                                    </a>
                                                    usuń
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <a href="/logged/avilablecourses">Dodaj Przedmiot</a>
                                    </c:if>
                                </div>
                            </div>
                        </div> <!-- / row -->
            </c:if>
                        <c:if test="${empty currentGroup && not empty currentCourse}">
                        <!-- ======= Features Section ======= -->
                        <section id="features" class="features">

                            <div class="container" data-aos="fade-up">

                                <header class="section-header">
                                    <p>Wybierz Klasę</p>
                                </header>

                                <div class="row">

                                    <div class="col-lg-6 mt-5 mt-lg-0 d-flex">
                                        <div class="row align-self-center gy-4">
                                            <c:if test="${courseGroups.size() == 0}">
                                                Przedmiot ${currentCourse.name} nie jest prowadzony w żadnej klasie
                                                <a href="/logged/groupstobook">Dodaj klasę do przedmiotu</a>
                                            </c:if>
                                            <c:if test="${courseGroups.size() != 0}">
                                                <c:forEach var = "group" items="${courseGroups}">
                                                    <div class="col-md-6" data-aos="zoom-out" data-aos-delay="700">
                                                        <div class="feature-box d-flex align-items-center">
                                                            <a href="/logged/currentgroup/${group.id}">
                                                                <h3>${group.name}</h3>
                                                            </a></li>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                                <a href="/logged/groupstobook">Dodaj klasę do przedmiotu</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </div> <!-- / row -->
                                </c:if>
                                 <c:if test="${not empty currentGroup && not empty currentCourse}">
                                  <section id="features" class="features">

                                      <div class="container" data-aos="fade-up">

                                          <header class="section-header">
                                              <p>Wybiesz ucznia</p>
                                          </header>

                                          <div class="row">

                                              <div class="col-lg-6 mt-5 mt-lg-0 d-flex">
                                                  <div class="row align-self-center gy-4">
                                                      <c:if test="${groupsStudents.size() == 0}">
                                                          W klasie ${currentGroup.name} nie ma jeszcze zapisanych uczów
                                                        <a href="/logged/studentswithoutgroup">Zapisz ucznia do klasy</a>
                                                    </c:if>
                                                    <c:if test="${groupsStudents.size() != 0}">
                                                        <c:forEach var = "student" items="${groupsStudents}">
                                                            <div class="col-md-6" data-aos="zoom-out" data-aos-delay="700">
                                                                <div class="feature-box d-flex align-items-center">
                                                                    <a href="/logged/createnote/${student.id}">
                                                                        <h3>${student.name}</h3>
                                                                    </a></li>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                        <a href="/logged/studentswithoutgroup">Zapisz ucznia do klasy</a>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                </c:if>
        </div>
    </section>

</main><!-- End #main -->

<!-- ======= Footer ======= -->
<footer id="footer" class="footer">
    <div class="container">
        <div class="copyright">
            &copy; Copyright <strong><span>FlexStart</span></strong>. All Rights Reserved
        </div>
        <div class="credits">
            <!-- All the links in the footer should remain intact. -->
            <!-- You can delete the links only if you purchased the pro version. -->
            <!-- Licensing information: https://bootstrapmade.com/license/ -->
            <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/flexstart-bootstrap-startup-template/ -->
            Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
        </div>
    </div>
</footer><!-- End Footer -->

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="../../assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="../../assets/vendor/aos/aos.js"></script>
<script src="../../assets/vendor/php-email-form/validate.js"></script>
<script src="../../assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="../../assets/vendor/purecounter/purecounter.js"></script>
<script src="../../assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
<script src="../../assets/vendor/glightbox/js/glightbox.min.js"></script>

<!-- Template Main JS File -->
<script src="../../assets/js/main.js"></script>

</body>

</html>