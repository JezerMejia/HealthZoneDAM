package register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jezerm.healthzone.databinding.FragmentRegisterHospitalBinding

class RegisterHospitalFragment: Fragment() {
    private lateinit var binding: FragmentRegisterHospitalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterHospitalBinding.inflate(inflater, container, false)
        return binding.root
    }
}