package net.onlineconsultations.web.admin.page;

import net.onlineconsultations.web.admin.form.SubjectForm;
import net.onlineconsultations.domain.Subject;
import net.onlineconsultations.service.SubjectService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = { "/admin/subjects", "/admin"})
public class SubjectsController {
    private static final String SUBJECTS_VIEW = "admin/subject/subjects";
    private static final String SUBJECT_FORM_VIEW = "admin/subject/subjectForm";

    @Inject
    private SubjectService subjectService;

    @RequestMapping(method = RequestMethod.GET)
    public String adminSubjects(Model model) {
        List<Subject> subjects = subjectService.getAll();
        model.addAttribute("subjects", subjects);

        return SUBJECTS_VIEW;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String subjectsAdd(Model model) {
        model.addAttribute("subject", new SubjectForm());

        return SUBJECT_FORM_VIEW;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String subjectsAddSubmit(@ModelAttribute("subject") @Valid SubjectForm subjectForm,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return SUBJECT_FORM_VIEW;
        }

        try {
            subjectService.save(new Subject(
                    subjectForm.getName(),
                    subjectForm.getDescription()
            ));
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("name",
                    "error.subject.name.nonunique",
                    "There is a subject with the same name.");
            return SUBJECT_FORM_VIEW;
        }

        return "redirect:/admin/subjects";
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
    public String subjectsRemove(@PathVariable("id") Long subjectId,
                                 RedirectAttributes redirectAttributes) {
        subjectService.remove(subjectId);

        return "redirect:/admin/subjects";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String subjectsEdit(@PathVariable("id") Long subjectId,
                               Model model) {
        model.addAttribute("subject", SubjectForm.of(subjectService.getById(subjectId)));

        return SUBJECT_FORM_VIEW;
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String subjectsEditSubmit(@PathVariable("id") Long subjectId,
                                     @ModelAttribute("subject") @Valid SubjectForm subjectForm,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return SUBJECT_FORM_VIEW;
        }

        Subject subject = subjectService.getById(subjectId);
        subject.setName(subjectForm.getName());
        subject.setDescription(subjectForm.getDescription());

        try {
            subjectService.merge(subject);
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("name",
                    "error.subject.name.nonunique",
                    "There is a subject with the same name.");
            return SUBJECT_FORM_VIEW;
        }

        return "redirect:/admin/subjects";
    }
}
