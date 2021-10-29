package com.project.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class RestController {
    private final DepartmentRepository departmentRepository;
    private final TeacherRepository teacherRepository;
    private final PlacementRepository placementRepository;
    private final CourseRepository courseRepository;
    private final TimetableRepository timetableRepository;
    private final CourseplacementsRepository courseplacementsRepository;
    private final BatchRepository batchRepository;
    private final SectionsRepository sectionsRepository;
    public RestController(DepartmentRepository repository,
                          TeacherRepository teacherRepository, PlacementRepository placementRepository,
                          CourseRepository courseRepository, TimetableRepository timetableRepository,
                          CourseplacementsRepository courseplacementsRepository, BatchRepository batchRepository, SectionsRepository sectionsRepository) {
        departmentRepository = repository;
        this.teacherRepository = teacherRepository;
        this.placementRepository = placementRepository;
        this.courseRepository = courseRepository;
        this.timetableRepository = timetableRepository;
        this.courseplacementsRepository = courseplacementsRepository;
        this.batchRepository = batchRepository;
        this.sectionsRepository = sectionsRepository;
    }

    @GetMapping("/getBatches")
    public @ResponseBody
    List<Integer> getBatches() {
        return batchRepository.findAll().stream().map(Batch::getId).collect(Collectors.toList());
    }

    @GetMapping("/getAllDepartments")
    public @ResponseBody
    List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/getPlacementsByDept/{d}")
    public @ResponseBody
    List<Placement> getPlacementsByDept(@PathVariable String d) {
        return placementRepository.findByDepartment_Id(d);
    }

    @GetMapping("/getPlacementsByDepartmentAndBatch/{d}/{b}")
    public @ResponseBody
    List<Placement> getPlacementsByDepartmentAndBatch(@PathVariable String d, @PathVariable Integer b) {
        System.out.println(d + " " + b + " " + placementRepository.findByDepartment_IdAndBatch_Id(d, b));
        return placementRepository.findByDepartment_IdAndBatch_Id(d, b);
    }



    @GetMapping("/getPlacementsByDepartmentAndBatchAndSection/{d}/{b}/{s}")
    public @ResponseBody
    List<Placement> getPlacementsByDepartmentAndBatchAndSection(@PathVariable String d, @PathVariable Integer b, @PathVariable Integer s) {
        System.out.println(d + " In " + b + " " + s + " " + placementRepository.findByDepartment_IdAndBatch_IdAndSection(d, b, s));
        return placementRepository.findByDepartment_IdAndBatch_IdAndSection(d, b, s);
    }

    @GetMapping("/getTeachers/{d}")
    public @ResponseBody
    List<Teacher> getTeachersByDepartment(@PathVariable String d) {
        return teacherRepository.findByDepartmentID_Id(d);
    }

    @GetMapping("/getPlacement/{t}")
    public @ResponseBody
    List<Placement> getPlacements(@PathVariable String t) {
        return placementRepository.findByTeacherID_name(t);
    }

    @GetMapping("/getTimes/{d}/{b}")
    public @ResponseBody
    List<Timetable> getTimes(@PathVariable String d, @PathVariable String b) {
        return timetableRepository.findTimetableByPid_departmentIdAndPid_batch_id(d, Integer.parseInt(b));
    }

    @GetMapping("/getTeachersTimeTable/{t}")
    public @ResponseBody
    List<Timetable> getTeachersTimeTable(@PathVariable String t) {
        System.err.println(timetableRepository.findTimetableByPid_teacherID_name("intern"));
        return timetableRepository.findTimetableByPid_teacherID_name(t);
    }

    @GetMapping("/getTeacherClasses/{t}")
    public @ResponseBody
    List<Placement> getTeacherClasses(@PathVariable String t) {
        return new ArrayList<>(placementRepository.findByTeacherID_name(t));
    }

    @GetMapping("/findBatchCourses/{c}/{b}")
    public @ResponseBody
    List<Courseplacements> getBatchCourses(@PathVariable String c, @PathVariable int b) {
        return courseplacementsRepository.findByCourse_DepartmentID_IdAndBatch_Id(c, b);
    }

    @GetMapping("/GetCourses/{d}")
    public @ResponseBody List<Course> getCoursesByDepartment(@PathVariable String d) {
        return courseRepository.findByDepartmentID_Name(d);
    }

    @GetMapping("getSections/{d}/{b}")
    public @ResponseBody int getSections(@PathVariable String d, @PathVariable int b) {
        return sectionsRepository.findByDepartment_IdAndBatch_Id(d, b).stream().mapToInt(Sections::getSectioncount).sum();
    }

    @SafeVarargs
    private static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors)
    {
        final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

        return t ->
        {
            final List<?> keys = Arrays.stream(keyExtractors)
                    .map(ke -> ke.apply(t))
                    .collect(Collectors.toList());

            return seen.putIfAbsent(keys, Boolean.TRUE) == null;
        };
    }

    @PostMapping("/post")
    public @ResponseBody void addPlacement(@RequestBody List<PlacementDto> placementDtos) {
        System.err.println(placementDtos.size());
        List<Placement> placements = new ArrayList<>();
        placementDtos.forEach(placementDto -> {

            Placement placement = new Placement();
            placement.setTeacherID(teacherRepository.findByName(placementDto.getTeacherName()));
            placement.setBatch(batchRepository.findById(placementDto.getBatch()));
            placement.setCourseID(courseRepository.findById(placementDto.getCourseID()).get());
            placement.setDepartment(departmentRepository.findById(placementDto.getDepartment()).get());
            placement.setSection(placementDto.getSection());
            placement.setFormat(placementDto.getFormat());
            List<Placement> placementss = placementRepository.
                    findByCourseID_IdAndBatch_IdAndSectionAndFormat(placementDto.getCourseID(),
                            placementDto.getBatch(), placementDto.getSection(), placementDto.getFormat());
            if (placementss.size() > 0) {
                placementss.get(0).setTeacherID(placement.getTeacherID());
                placements.add(placementss.get(0));
            }
            else
                placements.add(placement);
        });
        System.err.println("Before");
        //List<Placement> ps = placementRepository.findAll();
        //ps.addAll(placements);
        //List<Placement> oplacements = ps.stream().filter(distinctByKeys(Placement::getBatch, Placement::getCourseID,
        //        Placement::getDepartment, Placement::getFormat, Placement::getTeacherID,
        //        Placement::getSection)).collect(Collectors.toList());
        System.err.println(placements.size());
        placementRepository.saveAll(placements);
    }

    @PostMapping("/post/timetable")
    public @ResponseBody void addTimetable(@RequestBody List<TimeTableDto> placementDtos) {
        List<Timetable> list = new ArrayList<>();
        placementDtos.forEach(t -> {
            Timetable tm = new Timetable();
            tm.setDay(t.getDay());
            tm.setStarttime(t.getStarttime());
            tm.setEndtime(t.getEndtime());
            tm.setPid(placementRepository.findById(t.getPid()));

            list.add(tm);
        });
    }
}
