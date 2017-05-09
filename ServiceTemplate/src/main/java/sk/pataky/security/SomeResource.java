package sk.pataky.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.UUID;

/**
 *
 */

@RestController
@RequestMapping("/foo")
public class SomeResource {

    @GetMapping
    @PreAuthorize("hasAuthority('FOO_READ')")
    public String readFoo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return "read foo " + UUID.randomUUID().toString();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('FOO_WRITE')")
    public String writeFoo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return "write foo " + UUID.randomUUID().toString();
    }
}
