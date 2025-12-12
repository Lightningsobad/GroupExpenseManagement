package com.exercise.groupexpensemanagement.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.exercise.groupexpensemanagement.R;
import com.exercise.groupexpensemanagement.data.api.ApiService;
import com.exercise.groupexpensemanagement.data.model.Group;
import com.exercise.groupexpensemanagement.data.model.User;
import com.exercise.groupexpensemanagement.databinding.FragmentSignInBinding;
import com.exercise.groupexpensemanagement.ui.groupcreate.CreateAGroupActivity;
import com.exercise.groupexpensemanagement.ui.main.MainScreenActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInFragment extends Fragment {
    FragmentSignInBinding binding;

    private SignInViewModel signInViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentSignInBinding.inflate(getLayoutInflater(), container, false);
        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);



        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSignIn();
//                observeViewModel();
            }
        });

        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(SignInFragment.this).navigate(R.id.action_sign_up_to_sign_in);
            }
        });


        return binding.getRoot();
    }


    private void checkSignIn(){
        String username = binding.edtUsername.getText().toString();
        String password = binding.edtPassword.getText().toString();
        ApiService.apiService.getUser(username, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    if(!user.getNhoMs().isEmpty()){
                        Intent intent = new Intent(getActivity(), MainScreenActivity.class);
                        Group group = user.getNhoMs().get(0);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("group", group);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        requireActivity().finish();
                    } else{
                        Intent intent = new Intent(getActivity(), CreateAGroupActivity.class);
                        intent.putExtra("user_id", user.getMaNguoiDung());
                        startActivity(intent);
                        requireActivity().finish();
                    }
                } else {
                    Toast.makeText(getActivity(), "Wrong account or password!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), "Error Api!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void observeViewModel() {

        // 1) Quan sát login thành công hay thất bại
        signInViewModel.getIsLoginSuccessful().observe(getViewLifecycleOwner(), isSuccess -> {
            if (isSuccess == null) return;

            if (!isSuccess) {
                Toast.makeText(getActivity(), "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Khi login thành công, chờ kết quả kiểm tra nhóm
            Toast.makeText(getActivity(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
        });

        // 2) Quan sát user đã có nhóm hay chưa
        signInViewModel.getIsGroupCreated().observe(getViewLifecycleOwner(), hasGroup -> {
            if (hasGroup == null || signInViewModel.getUser().getValue() == null) return;
            User user = signInViewModel.getUser().getValue();

            if (hasGroup) {
//                // Đã có nhóm → vào MainActivity
//                Intent intent = new Intent(getActivity(), MainScreenActivity.class);
//                intent.putExtra("user_id", user.getMaNguoiDung());
//                startActivity(intent);
//                requireActivity().finish();
                Toast.makeText(getActivity(), "Đăng nhập thành công vaf co nhom!", Toast.LENGTH_SHORT).show();
            } else {
                // Chưa có nhóm → vào CreateAGroupActivity
                Intent intent = new Intent(getActivity(), CreateAGroupActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                requireActivity().finish();
            }
        });
    }


}
